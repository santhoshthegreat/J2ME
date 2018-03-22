import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import tettris.Resizer;
public class MyCanvas extends Canvas 
{
	private WoodenBlocks game;		
	private int boardX;				
	private int boardY;				
	private int boardWidth;				
	private int boardHeight;			
	public int blockSize;				
	private int infoPanelX;				
	private mybox scoreBox;			
	private mybox lineCountBox;		
	private mybox levelBox;		
	private ShowBlock nextPieceBox;	
	private tb titleBox;		
	private Font font;			
	private Image doubleBuffer;		
	private boolean paintedOnce;		
	private int[][] lastBoardState;		

        private Image blockimage;
        private Image bgimage;
	public MyCanvas(WoodenBlocks game) 
        {
		this.game = game;
		this.lastBoardState = new int[Constblock.WIDTH][Constblock.VIEWABLE_ROWS];	// only need to save state for visible rows
		
		if(!this.isDoubleBuffered())
                {

			this.doubleBuffer = Image.createImage(this.getWidth(), this.getHeight());
		}
		// initializes instance variables that set the relative layout
		this.setupLayout();
                
                try
                {
                    blockimage = Image.createImage("/block.jpg");                    
                    blockimage=Resizer.resizeImage(blockimage,this.blockSize-1,this.blockSize-1);
                    bgimage = Image.createImage("/mybg.jpg");                    
                }
                catch(Exception e)
                {
                    
                }                
	}

	protected void keyPressed(int keyCode) {
		this.game.keyPressed(keyCode);
	}       
        public void pointerReleased(int x,int y)
        {
            game.pointerReleased(x, y);
        }
        public void pointerPressed(int x,int y)
        {
            game.pointerPressed(x, y);
        }
        public void pointerDragged(int x,int y)
        {
            game.pointerDragged(x, y);
        }        
	public void paint(Graphics g) 
        {
		if(null == this.doubleBuffer) 
                {
			// direct support for double buffering, so we just paint directly to the canvas
			this.paintScreen(g);
		}
                else 
                {
			Graphics bufferG = this.doubleBuffer.getGraphics();
			this.paintScreen(bufferG);
			g.drawImage(this.doubleBuffer, 0 , 0, Graphics.LEFT | Graphics.TOP);
		}
	}
	private void paintScreen(Graphics g) {
		initboard board = this.game.getBoard();

		if(!this.paintedOnce) {
			// need to paint one time things that will not be updated during the normal course of the game
			this.paintOnce(g);
			this.paintedOnce = true;
			
		} else if(Constblock.RUNNING_STATE == this.game.getGameState()) {
			// otherwise if the game is running we need to update the board/info
			
			this.paintInfoBoxes(g);
			this.paintBoard(g);
		}
	}
	

	private void paintOnce(Graphics g) 
        {

                g.drawImage(bgimage,0,0,20);
		// paint the info boxes
		this.scoreBox.paint(g);
		this.lineCountBox.paint(g);
		this.levelBox.paint(g);
		this.nextPieceBox.paint(g);
		
		if(Constblock.TITLE_STATE == this.game.getGameState()) {
			// paint the title screen over the board
			this.paintBoard(g);
			this.titleBox.setHiScore(this.game.getHiScore());
			this.titleBox.paint(g);
			
		} else if(Constblock.RUNNING_STATE == this.game.getGameState()) {
			// just need to paint the game board
			this.paintBoard(g);
			
		} else if(Constblock.PAUSED_STATE == this.game.getGameState()) {
			// we paint a paused message and hide the board
			this.paintPausedBoard(g);
		}
	}
	
	private void paintInfoBoxes(Graphics g) 
        {
		
		// paint the score box only if its value has changed
		if(this.scoreBox.updateValue(this.game.getScore())) 
                {
			this.scoreBox.paint(g);
		}
		
		// paint the line count box only if its value has changed
		if(this.lineCountBox.updateValue(this.game.getLineCount())) 
                {
			this.lineCountBox.paint(g);
		}
		
		// paint the level box only if its value has changed
		if(this.levelBox.updateValue(this.game.getLevel())) 
                {
			this.levelBox.paint(g);
		}
		
		// paint the next piece box only if the next piece has changed
		if(this.nextPieceBox.setPieceType(this.game.getNextPieceType())) 
                {
			this.nextPieceBox.paint(g);
		}
	}
	private void paintPausedBoard(Graphics g) 
        {
		// fill the whole board area to hide it
		g.setColor(Constblock.EMPTY_COLOR);
		g.fillRect(this.boardX, this.boardY, this.boardWidth, this.boardHeight);
		
		// paint paused message at the center of the board
		int boardCenterX = this.boardX + (this.boardWidth / 2);
		int boardCenterY = this.boardY + (this.boardHeight / 2);
		
		g.setColor(Constblock.COLOR_BLACK);
		g.drawString("paused", boardCenterX, boardCenterY, Graphics.HCENTER | Graphics.BOTTOM);
	}
	private void paintBoard(Graphics g) 
        {
		initboard board = this.game.getBoard();
		
		for(int x = 0; x < Constblock.WIDTH; x++) 
                {
			for(int y = Constblock.TOP_VISIBLE_ROW; y < Constblock.HEIGHT; y++) 
                        {
				int blockType = board.getBlockType(x, y);
				
				// check if the state of the block is different from the last time we painted
				if(blockType != this.getLastBoardState(x, y) || Constblock.BLOCK_ACTIVE == blockType) 
                                {
					// repaint the block
					this.paintBlock(x, y, blockType, g);
					// update the saved state
					this.setLastBoardState(x, y, blockType);
				}
			}
		}
	}
	
	private int getLastBoardState(int x, int y) 
        {
		return this.lastBoardState[x][y - Constblock.TOP_VISIBLE_ROW];
	}
	
	private void setLastBoardState(int x, int y, int blockType) 
        {
		this.lastBoardState[x][y - Constblock.TOP_VISIBLE_ROW] = blockType;
	}

	private void paintBlock(int x, int y, int blockType, Graphics g) {
		int blockX = this.boardX + (this.blockSize * x);
		int blockY = this.boardY + (this.blockSize * (y - Constblock.TOP_VISIBLE_ROW));
		
		if(Constblock.BLOCK_EMPTY != blockType) 
                {
                        g.drawImage(blockimage,blockX,blockY,20);
		}
                else 
                {
			// block is empty, paint it the empty color
			g.setColor(Constblock.COLOR_WHITE);
			g.fillRect(blockX, blockY, this.blockSize, this.blockSize);
		}	
	}
	
	/**
	 * Reset the board state so that one-time painting will be done with next painting.
	 */
	public void reset() 
        {
		this.paintedOnce = false;
		// reset the board cached board state too
		for(int x = 0; x < Constblock.WIDTH; x++) 
                {
			for(int y = Constblock.TOP_VISIBLE_ROW; y < Constblock.HEIGHT; y++) 
                        {
				this.setLastBoardState(x, y, Constblock.UNINITIALIZED);
			}
		}
		this.repaint();
	}

        protected void sizeChanged(int w,int h)
        {
            this.lastBoardState = new int[Constblock.WIDTH][Constblock.VIEWABLE_ROWS];	// only need to save state for visible rows
            if(!this.isDoubleBuffered())
            {
                this.doubleBuffer = Image.createImage(this.getWidth(), this.getHeight());
            }
            this.setupLayout();
        }
	private void setupLayout() 
        {
		// screen dimensions
		this.font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
		int infoBoxHeight = 50;
		this.boardX = 0;
		this.boardY = 0;
                Constblock.WIDTH=14;
                Constblock.HEIGHT=20;
                Constblock.VIEWABLE_ROWS=Constblock.HEIGHT-2;
		int blockHeight = (getHeight()-50)/Constblock.HEIGHT;
                int blockWidth = blockHeight;
		this.blockSize = Math.min(blockWidth, blockHeight);
                this.boardWidth = blockSize * Constblock.WIDTH;
		this.boardHeight = blockSize * Constblock.VIEWABLE_ROWS;                

                this.boardY=((getHeight()+50)-boardHeight)/2;
                this.boardX=(getWidth()-boardWidth)/2;
		this.infoPanelX = this.boardX + this.boardWidth;

		this.infoPanelX = 0;
                int infoBoxY = 0;
	
                int w=(getWidth()/4);
                this.scoreBox = new mybox(this.infoPanelX, infoBoxY,w, infoBoxHeight,
									Constblock.COLOR_BLACK, 0x0000ff00,
									this.font, "score", this.game.getScore());
                this.infoPanelX=this.infoPanelX+w;
		this.levelBox = new mybox(this.infoPanelX , infoBoxY,w, infoBoxHeight,
									Constblock.COLOR_BLACK, 0x0000ff00,
									this.font, "level", this.game.getLevel());
                this.infoPanelX=this.infoPanelX+w;                
		this.lineCountBox = new mybox(this.infoPanelX , infoBoxY, w, infoBoxHeight,
										Constblock.COLOR_BLACK, 0x0000ff00,
										this.font, "lines", this.game.getLineCount());
                this.infoPanelX=this.infoPanelX+w;                                
		this.nextPieceBox = new ShowBlock(this.infoPanelX , infoBoxY, w, 50,Constblock.COLOR_BLACK, 0x0000ff00, this.font);
		this.titleBox = new tb(0, 0, getWidth(), getHeight(),Constblock.COLOR_BLACK, Constblock.COLOR_LIGHT_GREY,this.font);                               
	}
}
