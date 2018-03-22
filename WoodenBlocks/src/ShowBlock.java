import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
/**
 * A DisplayBox that shows the next piece.
 */
public class ShowBlock extends DisplayBox {
	
	private int pieceType;		
	private Font captionFont;	
	private int leftBlockX;		
	private int blockSize;		
	public ShowBlock(int x, int y, int width, int height,
						int fgColor, int bgColor,
						Font captionFont) {
		super(x, y, width, height, fgColor, bgColor);
		
		this.captionFont = captionFont;
	
		this.setupLayout();
	}
	
	/**
	 * One time method to set up the layout in the box, ie how big the blocks are, positioning, etc...
	 */
	private void setupLayout() 
        {
		
		final int gridSize = 6;
		
		this.blockSize = Math.min(this.width / gridSize, this.height / gridSize);
		
		this.leftBlockX = this.x + ((this.width - (this.blockSize * gridSize)) / 2);
	}

	public boolean setPieceType(int pieceType) {
		if(this.pieceType != pieceType) {
			this.pieceType = pieceType;
			return true;
		}

		return false;
	}
        private void paintbg(Graphics g)
        {
// I piece
            	int color = Constblock.COLOR_WHITE;
		
		int x = this.leftBlockX + this.blockSize;
		int y = (this.y + this.height) - (this.blockSize * 2);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y, this.blockSize, color, g);
		this.paintBlock(x + (2 * this. blockSize), y, this.blockSize, color, g);
		this.paintBlock(x + (3 * this.blockSize), y, this.blockSize, color, g);
                
                
		this.paintBlock(x, y- this.blockSize, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y- this.blockSize, this.blockSize, color, g);
		this.paintBlock(x + (2 * this. blockSize), y- this.blockSize, this.blockSize, color, g);
		this.paintBlock(x + (3 * this.blockSize), y- this.blockSize, this.blockSize, color, g);
                
		this.paintBlock(x, y- (2*this.blockSize), this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y- (2*this.blockSize), this.blockSize, color, g);
		this.paintBlock(x + (2 * this. blockSize), y- (2*this.blockSize), this.blockSize, color, g);
		this.paintBlock(x + (3 * this.blockSize), y- (2*this.blockSize), this.blockSize, color, g);                
                
               // o piece
                
		
		x = this.leftBlockX + (this.blockSize * 2);
		y = (this.y + this.height) - (this.blockSize * 3);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y + this.blockSize, this.blockSize, color, g);
              // t piece
                
		
		x = this.leftBlockX + (this.blockSize * 3);
		y = (this.y + this.height) - (this.blockSize * 3);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x - this.blockSize, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y + this.blockSize, this.blockSize, color, g);
                
               // s [piece
                
	
		x = this.leftBlockX + (this.blockSize * 2);
		y = (this.y + this.height) - (this.blockSize * 3);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y, this.blockSize, color, g);
		this.paintBlock(x - this.blockSize, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
                
                // z
                
		x = this.leftBlockX + this.blockSize;
		y = (this.y + this.height) - (this.blockSize * 3);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x + (this.blockSize * 2), y + this.blockSize, this.blockSize, color, g);                
                
                // l
                
		x = this.leftBlockX + (this.blockSize * 2);
		y = (this.y + this.height) - (this.blockSize * 4);
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x, y + (this.blockSize * 2), this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y + (this.blockSize * 2), this.blockSize, color, g);
                
                // j
		x = this.leftBlockX + (this.blockSize * 3);
		y = (this.y + this.height) - (this.blockSize * 4);
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x, y + (this.blockSize * 2), this.blockSize, color, g);
		this.paintBlock(x - this.blockSize, y + (this.blockSize * 2), this.blockSize, color, g);
        }
	protected void paintBoxContents(Graphics g) 
        {
		// write the "next" caption in the foreground color
		g.setColor(this.fgColor);
		int xCenter = this.x + (this.width / 2);
		g.setFont(this.captionFont);
		g.drawString("next", xCenter, this.y, Graphics.TOP | Graphics.HCENTER);
		
                
                paintbg(g);
		// paint the piece in the box
		switch(this.pieceType) {
			case Constblock.I_PIECE:
				this.paintIPiece(g);
				break;
			case Constblock.O_PIECE:
				this.paintOPiece(g);
				break;
			case Constblock.T_PIECE:
				this.paintTPiece(g);
				break;
			case Constblock.S_PIECE:
				this.paintSPiece(g);
				break;
			case Constblock.Z_PIECE:
				this.paintZPiece(g);
				break;
			case Constblock.L_PIECE:
				this.paintLPiece(g);
				break;
			case Constblock.J_PIECE:
				this.paintJPiece(g);
				break;
		}
	}
	private void paintBlock(int x, int y, int blockSize, int color, Graphics g) {
		g.setColor(color);
		g.fillRect(x + 1, y + 1, blockSize - 1, blockSize - 1);
	}
	
	private void paintIPiece(Graphics g) {
		int color = Constblock.COLOR_BLACK;            		
		int x = this.leftBlockX + this.blockSize;
		int y = (this.y + this.height) - (this.blockSize * 2);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y, this.blockSize, color, g);
		this.paintBlock(x + (2 * this. blockSize), y, this.blockSize, color, g);
		this.paintBlock(x + (3 * this.blockSize), y, this.blockSize, color, g);
                                
	}
	
	private void paintOPiece(Graphics g) {

		int color = Constblock.COLOR_BLACK;            		
		int x = this.leftBlockX + (this.blockSize * 2);
		int y = (this.y + this.height) - (this.blockSize * 3);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y + this.blockSize, this.blockSize, color, g);
	}
	private void paintTPiece(Graphics g) {
//		int color = Constblock.T_PIECE_COLOR;
		int color = Constblock.COLOR_BLACK;            		
		int x = this.leftBlockX + (this.blockSize * 3);
		int y = (this.y + this.height) - (this.blockSize * 3);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x - this.blockSize, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y + this.blockSize, this.blockSize, color, g);
	}
	
	private void paintSPiece(Graphics g) {
//		int color = Constblock.S_PIECE_COLOR;
		int color = Constblock.COLOR_BLACK;            		
		int x = this.leftBlockX + (this.blockSize * 2);
		int y = (this.y + this.height) - (this.blockSize * 3);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y, this.blockSize, color, g);
		this.paintBlock(x - this.blockSize, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
	}
	private void paintZPiece(Graphics g) {
//		int color = Constblock.Z_PIECE_COLOR;
		int color = Constblock.COLOR_BLACK;            		
		int x = this.leftBlockX + this.blockSize;
		int y = (this.y + this.height) - (this.blockSize * 3);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y, this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x + (this.blockSize * 2), y + this.blockSize, this.blockSize, color, g);
	}
	
	private void paintLPiece(Graphics g) {
//		int color = Constblock.L_PIECE_COLOR;
		int color = Constblock.COLOR_BLACK;            		
		int x = this.leftBlockX + (this.blockSize * 2);
		int y = (this.y + this.height) - (this.blockSize * 4);
		
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x, y + (this.blockSize * 2), this.blockSize, color, g);
		this.paintBlock(x + this.blockSize, y + (this.blockSize * 2), this.blockSize, color, g);
	}
	private void paintJPiece(Graphics g) {
//		int color = Constblock.J_PIECE_COLOR;
		int color = Constblock.COLOR_BLACK;            
		int x = this.leftBlockX + (this.blockSize * 3);
		int y = (this.y + this.height) - (this.blockSize * 4);
		this.paintBlock(x, y, this.blockSize, color, g);
		this.paintBlock(x, y + this.blockSize, this.blockSize, color, g);
		this.paintBlock(x, y + (this.blockSize * 2), this.blockSize, color, g);
		this.paintBlock(x - this.blockSize, y + (this.blockSize * 2), this.blockSize, color, g);
	}
}
