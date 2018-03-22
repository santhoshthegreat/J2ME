import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Random;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
public class WoodenBlocks extends MIDlet implements CommandListener 
{
	private MyCanvas gameCanvas;		// canvas on which the game is painted	
	private initboard board;				// holds the game state
	private spriteblock activePiece;		// holds the state of the active piece
	private int score;						// the current game score
	private int level;						// the current level
	private int lineCount;					// the current number of lines cleared
	private int nextPieceType;				// the next piece
	private int tickSpeed;					// the speed in milliseconds between drops
        private int hiScore;					// the current hi score
	private RecordStore tetrisStore;		// the RecordStore holding the hi score
	private int hiScoreRecordId;			// the record id in the RecordStore of the hi score
	private boolean[] completedRows;		// array of booleans indicating the rows that have been cleared
						// store as an instance variable so we reuse without reallocating
	private Random rand;					// generates pseudo random numbers to choose the next piece
	private Command exitCommand;			// Command to exit the app
	private Command pauseCommand;			// Command to pause the app
	private Command resumeCommand;			// Command to resume the app after a pause
	private int gameState = Constblock.UNINITIALIZED;	// mark as unitialized at first, can check in startApp to see if init necessary
        private TickerThread dropThread;			// the thread that drops the active piece one row per tick
	/**
	 * Start the app.
	 * @see MIDlet#startApp()
	 */
        
        private Display display;
        private MenuScreen mnu;
        boolean started;
        public int unlockedlevel;
        public boolean soundstate;
        public int curscr=0;

        
        private int prevx=0;
        private int prevy=0;
        
        public WoodenBlocks()
        {
            unlockedlevel=12;        
            soundstate=true;
            curscr=0;      
            mnu=new MenuScreen(this);                                            
            display=Display.getDisplay(this);
        }
        public void displaygame()
        {
            display.setCurrent(this.gameCanvas);
        }
        private void displaySplashScreen()
        {
            SplashScreen s=new SplashScreen(this);
            Display.getDisplay(this).setCurrent(s);
        }        
	protected void startApp()
        {
                displaySplashScreen();
		if(Constblock.UNINITIALIZED == this.gameState) 
                {
			// game is just starting up, need to initialize
			this.init();
		}
                else if(Constblock.RUNNING_STATE == this.gameState) 
                {
			this.resumeGame();
		}
                else 
                {
			this.gameCanvas.reset();
		}
	}
	protected void pauseApp() 
        {
		if(Constblock.RUNNING_STATE == this.gameState) 
                {
			this.pauseGame();
		}
	}
	protected void destroyApp(boolean unconditional)
        {
		if(null != this.tetrisStore) 
                {
			// write out the hi score before exitting
			this.writeAndCloseHiScore(this.hiScore);
		}
                notifyDestroyed();
	}
	/**
	 * Initalization on app startup.
	 */
	private void init() 
        {
		this.board = new initboard();
		this.gameCanvas = new MyCanvas(this);
		this.activePiece = new spriteblock();		
		this.completedRows = new boolean[Constblock.HEIGHT];
		this.hiScore = this.openAndReadHiScore();	// get currently saved hi score from rms
		this.nextPieceType = Constblock.UNINITIALIZED;
		this.rand = new Random();
		// setup exit/pause/resume commands
		this.setupCommands();
		this.gameCanvas.addCommand(this.exitCommand);
		// put the app in a state to show the title screen
		this.setGameState(Constblock.TITLE_STATE);
	}
	/**
	 * Set up the game state so that a new game is started.
	 * @param level the initial level at which to start the game
	 */
	private void startNewGame(int level) 
        {
		this.gameCanvas.addCommand(this.pauseCommand);	// during running, pause command should be available
		this.score = 0;
		this.lineCount = 0;
		this.level = level;
		this.nextPieceType = this.getRandomPieceType();
		this.tickSpeed = this.getInitialTickSpeed(this.level);
		this.board.clearBoard();
		this.tryAddNewPiece();
		this.setGameState(Constblock.RUNNING_STATE);
		this.runDropThread();
	}
	/**
	 * Put the app in an end game state.  Should redisplay the title screen.
	 */
	private void endGame() 
        {
		// no commands needed, don't need to pause when not playing
		this.gameCanvas.removeCommand(this.pauseCommand);
		this.hiScore = Math.max(this.hiScore, this.score);		// set hi score if current score is higher
		this.nextPieceType = Constblock.UNINITIALIZED;
		this.setGameState(Constblock.TITLE_STATE);							// show the title screen
		this.dropThread.stopThread();
		this.dropThread = null;		// will have to replace it anyway, might as well gc as soon as possible
	}
	/**
	 * Put the game into a paused state during running.
	 */
	private void pauseGame() {
		// replace the pause command with a resume command
		this.gameCanvas.removeCommand(this.pauseCommand);
		this.gameCanvas.addCommand(this.resumeCommand);
		// put in paused state and stop dropping
		this.setGameState(Constblock.PAUSED_STATE);
		this.dropThread.stopThread();
	}
	/**
	 * Resume the game from a paused state.
	 */
	private void resumeGame() {
		// replace the resume command with a pause command
		this.gameCanvas.removeCommand(this.resumeCommand);
		this.gameCanvas.addCommand(this.pauseCommand);
		// put in running state and resume dropping
		this.setGameState(Constblock.RUNNING_STATE);
		this.runDropThread();
	}
	/**
	 * Run the drop thread.  In MIDP 1.0 it doesn't seem possible to restart a thread.
	 * Instead just create a new one and start it.
	 */
	private void runDropThread() {
		this.dropThread = new TickerThread(this);
		this.dropThread.start();
	}
	/**
	 * Set the initial tick speed according to the given level.
	 * 
	 * @param level the initial level
	 * @return the initial tick speed
	 */
	private int getInitialTickSpeed(int level) {
		int initialTickSpeed = Constblock.BASE_SPEED;
		
		// multiply by fraction for each level
		for(int i = 0; i < level; i++) {
			initialTickSpeed = (initialTickSpeed * Constblock.SPEED_INCREASE_NUMERATOR) / Constblock.SPEED_INCREASE_DENOMINATOR;
		}
		return initialTickSpeed;
	}

	public void keyPressed(int keyCode) 
        {
		if(Constblock.RUNNING_STATE == this.gameState) 
                {
			// if the app is in a running state, then we want the game actions
			keyCode = this.gameCanvas.getGameAction(keyCode);	
			if(Canvas.DOWN == keyCode) 
                        {
                                boolean cnd=true;
                                while(cnd)
                                {
                                    cnd=this.tryMoveDown();
                                }
			}
                        else if(Canvas.UP == keyCode) 
                        {
				this.tryRotateLeft();
			} else if(Canvas.LEFT == keyCode) 
                        {
				this.tryMoveLeft();
			} else if(Canvas.RIGHT == keyCode) 
                        {
				this.tryMoveRight();
			}
                        else if(Canvas.FIRE == keyCode) 
                        {
				this.quickDrop();
			}
		}
                else if(Constblock.TITLE_STATE == this.gameState) 
                {
			// if we're at the title screen, get the level number from input
			if(Canvas.KEY_NUM0 <= keyCode && Canvas.KEY_NUM9 >= keyCode) 
                        {
			//	int level = keyCode - Canvas.KEY_NUM0;	
			//	this.startNewGame(level);
                                this.showmenu();
			}
		}	
	}
        
        protected void pointerPressed(int x, int y)
        {
            System.out.println("pressed");                                                                    
            if(Constblock.TITLE_STATE == this.gameState)
            {
                this.showmenu();
            }
            else if(Constblock.RUNNING_STATE == this.gameState) 
            {
                spriteblock activePiece = this.getActivePiece();                
                int xx= activePiece.getBlockX(0);
                int yy= activePiece.getBlockY(0);                   
                if(x>=(xx*gameCanvas.blockSize) && x<((xx*gameCanvas.blockSize)+(gameCanvas.blockSize*6)) && y>=(yy*gameCanvas.blockSize) && y<((yy*gameCanvas.blockSize)+(gameCanvas.blockSize*6)))
                {
                    prevx=x;
                    prevy=y;
                }
                else
                {
                    prevx=0;
                    prevy=0;
                }
/*                else if(x<=(gameCanvas.getWidth())/2)
                {
                    this.tryMoveLeft();
                }
                else if(x>(gameCanvas.getWidth())/2)
                {
                    this.tryMoveRight();
                }          
*/
            }            
        }
        public void pointerDragged(int x,int y)
        {
            if(Constblock.RUNNING_STATE == this.gameState) 
            {

            }
        }        
        public void pointerReleased(int x,int y)
        {
            if(Constblock.TITLE_STATE == this.gameState)
            {
                this.showmenu();
            }
            else if(Constblock.RUNNING_STATE == this.gameState) 
            {
                if(prevx!=0 && prevy!=0)
                {
                    if(x==prevx && y==prevy)
                    {
                        this.tryRotateRight();
                    }
                    else if(x>(prevx+gameCanvas.blockSize) )
                    {
                        if(x>(prevx+(gameCanvas.blockSize*3)))
                        {
                            this.tryMoveRight();                                                    
                            this.tryMoveRight();                                                                                
                        }
                        else
                        {
                            this.tryMoveRight();                        
                        }

                    } 
                    else if(x<(prevx-gameCanvas.blockSize) )
                    {
                        if(x<(prevx-(gameCanvas.blockSize*3)))
                        {
                            this.tryMoveLeft();
                            this.tryMoveLeft();
                        }
                        else
                        {
                            this.tryMoveLeft();
                        }                        
                    }                                       
                }
                prevx=x;
                prevy=y;            
/*                else if(x<=(gameCanvas.getWidth())/2)
                {
                    this.tryMoveLeft();
                }
                else if(x>(gameCanvas.getWidth())/2)
                {
                    this.tryMoveRight();
                }          
*/

            }
        }
               
	/**
	 * Quick drop the active piece as far as it will go
	 */
	private synchronized void quickDrop() 
        {
		int dropScore = 0;	// 1 point for each line dropped
		while(this.tryMoveDown()) 
                {
			dropScore++;
			this.dropThread.skipNextTick();                        
		}
		this.score += dropScore;
		if(null != this.dropThread) 
                {
			this.dropThread.skipNextTick();
		}
	}
	private spriteblock newPiece() {
		int pieceType = this.nextPieceType;
		this.nextPieceType = this.getRandomPieceType();
		spriteblock activePiece = this.getActivePiece();
		activePiece.setAsNewPiece(pieceType, Constblock.START_X, Constblock.START_Y);	
		return activePiece;
	}

	private int getRandomPieceType() {
		return Math.abs(rand.nextInt() % Constblock.NUM_PIECE_TYPES) + 1;
	}
	private int clearCompletedRows(spriteblock piece) 
        {
		initboard board = this.getBoard();
		for(int i = 0; i < Constblock.FOUR_BLOCKS; i++) 
                {
			int rowY = piece.getBlockY(i);
			if(board.checkRowCompleted(rowY)) 
                        {
				this.markRowCompleted(rowY, true);
			}
		}
		int numClearedRows = 0;
		for(int y = Constblock.HEIGHT - 1; y >= 0; y--) { // iterate from bottom up
			if(numClearedRows > 0) 
                        {
				board.dropRow(y, numClearedRows);
			}
			if(this.isRowCompleted(y)) 
                        {
				numClearedRows++;
				this.markRowCompleted(y, false);	// reset for next time
			}
		}
		// clear the top number of rows completed, these are new empty rows
		for(int i = 0; i < numClearedRows; i++) {
			board.clearRow(i);
		}
	      	return numClearedRows;
	}
	private void markRowCompleted(int row, boolean isCompleted) {
		this.completedRows[row] = isCompleted;
	}
	private boolean isRowCompleted(int row) 
        {
		return this.completedRows[row];
	}
	private void updateRowState(int completedRows) 
        {
		this.lineCount += completedRows;	// increment the line count
		if(1 == completedRows) {
			this.score += (this.level * Constblock.ONE_ROW_SCORE) + Constblock.ONE_ROW_SCORE;
		} else if(2 == completedRows) {
			this.score += (this.level * Constblock.TWO_ROW_SCORE) + Constblock.TWO_ROW_SCORE;
		} else if(3 == completedRows) {
			this.score += (this.level * Constblock.THREE_ROW_SCORE) + Constblock.THREE_ROW_SCORE;
		} else if(4 == completedRows) {
			this.score += (this.level *Constblock. FOUR_ROW_SCORE) + Constblock.FOUR_ROW_SCORE;
		}
		
		// integer division gets the level
		int level = this.lineCount / Constblock.LEVEL_UNIT;
		if(level > this.level) {
			this.level = level;
		
			// level increase, adjust tick speed
			this.tickSpeed = (this.tickSpeed * Constblock.SPEED_INCREASE_NUMERATOR) / Constblock.SPEED_INCREASE_DENOMINATOR;
		}
	}
	private synchronized boolean tryAddNewPiece() 
        {
		spriteblock newPiece = this.newPiece();		// reset the active piece
		initboard board = this.getBoard();
		if(board.canAddNewPiece(newPiece)) 
                {
			board.addNewPiece(newPiece);
			return true;
		}
		this.endGame();
		return false;
	}
	private synchronized boolean tryMoveDown() 
        {
		spriteblock activePiece = this.getActivePiece();
		initboard board = this.getBoard();	
		if(board.canMoveDown(activePiece))
                {
			board.moveDown(activePiece);
			this.gameCanvas.repaint();
			return true;
		}
		board.lockPiece(activePiece);
		int numClearedRows = this.clearCompletedRows(activePiece);
		this.updateRowState(numClearedRows);
		this.tryAddNewPiece();
		this.gameCanvas.repaint();
		return false;
	}
	private synchronized boolean tryMoveLeft() 
        {
		if(this.getBoard().canMoveLeft(this.getActivePiece())) 
                {
			this.board.moveLeft(this.getActivePiece());
			this.gameCanvas.repaint();
			return true;
		}
		return false;
	}
	private synchronized boolean tryMoveRight() 
        {
		if(this.getBoard().canMoveRight(this.getActivePiece())) 
                {
			this.board.moveRight(this.getActivePiece());
			this.gameCanvas.repaint();
			return true;
		}
		// couldn't move right
		return false;
	}
	private synchronized boolean tryRotateLeft() {
		if(this.getBoard().canRotateLeft(this.getActivePiece())) {
			this.board.rotateLeft(this.getActivePiece());
			this.gameCanvas.repaint();
			// rotated left
			return true;
		}
		// couldn't rotate
		return false;
	}
	/**
	 * Try to rotate the piece right (clockwise)
	 *
	 * @return true if we could rotate right, false if couldn't
	 */
	private synchronized boolean tryRotateRight() {
		if(this.getBoard().canRotateRight(this.getActivePiece())) {
			this.board.rotateRight(this.getActivePiece());
			this.gameCanvas.repaint();
			// rotated right
			return true;
		}
		// couldn't rotate
		return false;
	}

	public void commandAction(Command c, Displayable d) 
        {
		if(c == this.exitCommand) 
                {
				// on exit just destroy the app
                    this.showmenu();
		}
                else if(c == this.pauseCommand) 
                {
			this.pauseGame();
		}
                else if(c == this.resumeCommand) 
                {
			this.resumeGame();
		}
	}

	private void setupCommands() 
        {
		this.exitCommand = new Command("Menu", Command.EXIT, 1);
		this.pauseCommand = new Command("pause", Command.ITEM, 1);
		this.resumeCommand = new Command("resume", Command.ITEM, 1);
		this.gameCanvas.setCommandListener(this);
	}
	public void tick() 
        {
		this.tryMoveDown();
	}
	public initboard getBoard() 
        {
		return this.board;
	}
	public spriteblock getActivePiece() 
        {
		return this.activePiece;
	}
	/**
	 * @return the current game score
	 */
	public int getScore() 
        {
		return this.score;
	}
	/**
	 * @return the current hi score
	 */
	public int getHiScore() 
        {
		return this.hiScore;
	}
	/**
	 * @return the current line count
	 */
	public int getLineCount() 
        {
		return this.lineCount;
	}
	/**
	 * @return the current level
	 */
	public int getLevel() 
        {
		return this.level;
	}
	
	/**
	 * @return the upcoming piece once the current piece is dropped
	 */
	public int getNextPieceType() 
        {
		return this.nextPieceType;
	}
	
	/**
	 * @return the current time between ticks (milliseconds)
	 */
	public int getTickSpeed() 
        {
		return this.tickSpeed;
	}
	
	/**
	 * @return the current game state, should be a constant defined in the constants file
	 */
	public int getGameState() 
        {
		return this.gameState;
	}
	public void setGameState(int gameState) 
        {
		this.gameState = gameState;
		if(null != this.gameCanvas) 
                {
			this.gameCanvas.reset();
			this.gameCanvas.repaint();
		}
	}
	private int openAndReadHiScore() 
        {
		int hiScore = 0;
		try 
                {
			this.tetrisStore = RecordStore.openRecordStore(Constblock.TETRIS_RECORD_STORE, true);
			if(this.tetrisStore.getNumRecords() > 0) 
                        {
				// should be only one record, the hi score
				RecordEnumeration recordEnum = this.tetrisStore.enumerateRecords(null, null, false);
				this.hiScoreRecordId = recordEnum.nextRecordId();
				byte[] hiScoreBytes = this.tetrisStore.getRecord(this.hiScoreRecordId);
				// wrap the bytes in a DataInputStream so we can readInt
				ByteArrayInputStream byteIn = new ByteArrayInputStream(hiScoreBytes);
				DataInputStream dataIn = new DataInputStream(byteIn);
				hiScore = dataIn.readInt();
				// don't think this is necessary
				dataIn.close();
				byteIn.close();
			}
		}
                catch(Exception e) 
                {
			// nothing we can do, hiScore remains 0
		}
		return hiScore;
	}
	private void writeAndCloseHiScore(int score) 
        {
		try 
                {
			// use DataOutputStream so we can writeInt
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream(4);	// a single int, should be 4 bytes
			DataOutputStream dataOut = new DataOutputStream(byteOut);
			dataOut.writeInt(this.hiScore);
			// get the bytes for the int
			byte[] hiScoreBytes = byteOut.toByteArray();
			if(this.tetrisStore.getNumRecords() == 0) 
                        {
				// no previously stored record, created a new one
				this.tetrisStore.addRecord(hiScoreBytes, 0, hiScoreBytes.length);
			}
                        else 
                        {
				// overwrite the existing hi score record
				this.tetrisStore.setRecord(this.hiScoreRecordId, hiScoreBytes, 0, hiScoreBytes.length);
			}
			this.tetrisStore.closeRecordStore();
			dataOut.close();
			byteOut.close();
		}
                catch(Exception e) 
                {
			// oh well...
		}
	}
        
        
        public void showmenu()
        {
            curscr=0;
            display.setCurrent(mnu);
        }
        public void showCanvaslevel(int lvl)
        {
            displaygame();
            level=lvl;
            try
            {
                this.endGame();                                
            }
            catch(Exception e)
            {
            }
            this.startNewGame(level);
        }
        public void exitMIDlet(boolean state)
        {
            destroyApp(false);
        }

}
