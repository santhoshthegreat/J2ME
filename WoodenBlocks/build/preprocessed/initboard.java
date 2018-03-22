public class initboard 
{
	private int[][] boardBlocks = new int[Constblock.WIDTH][Constblock.HEIGHT];	// the grid of blocks
	public initboard() 
        {
		this.clearBoard();
	}
	public void clearBoard() 
        {
		for(int y = 0; y < Constblock.HEIGHT; y++) 
                {
			this.clearRow(y);
		}
	}
	public void clearRow(int rowY) 
        {
		for(int x = 0; x < Constblock.WIDTH; x++) 
                {
			this.setBlockType(x, rowY, Constblock.BLOCK_EMPTY);
		}
	}
	public void lockPiece(spriteblock piece) 
        {
		int pieceType = piece.getPieceType();	
		this.updatePieceBlocks(piece, pieceType);
	}
	public boolean checkRowCompleted(int rowY) 
        {
		for(int x = 0; x < Constblock.WIDTH; x++) 
                {
			if(Constblock.BLOCK_EMPTY == this.getBlockType(x, rowY)) 
                        {
				return false;
			}
		}
		return true;
	}
	
	public void dropRow(int rowY, int numRows) 
        {
		for(int x = 0; x < Constblock.WIDTH; x++) 
                {
			int blockType = this.getBlockType(x, rowY);	
			this.setBlockType(x, rowY + numRows, blockType);
		}
	}
	public int getBlockType(int x, int y) 
        {
		return this.boardBlocks[x][y];
	}
	private void setBlockType(int x, int y, int pieceType) 
        {
		this.boardBlocks[x][y] = pieceType;
	}
	private boolean checkBlockMove(int x, int y) 
        {
		if(this.isOnBoard(x, y)) { 
			int blockType = this.getBlockType(x, y);
			return Constblock.BLOCK_EMPTY  == blockType  || // block is empty OR
			Constblock.BLOCK_ACTIVE == blockType;    // already part of the piece
		}
		return false; // off the board
	}
	private boolean isOnBoard(int x, int y) {
		return x >= 0 		&&		// not off the left edge
		  	   x <  Constblock.WIDTH	&&		// not off the right edge
			   y >= 0 		&&		// not off the bottom
			   y <  Constblock.HEIGHT;			// not already at the top? can't move up anyway...
	}
	private void updatePieceBlocks(spriteblock piece, int blockType) 
        {
		// set each of the four blocks
		for(int i = 0; i < Constblock.FOUR_BLOCKS; i++) 
                {
			int blockX = piece.getBlockX(i);
			int blockY = piece.getBlockY(i);
			this.setBlockType(blockX, blockY, blockType);
		}
	}
	public boolean canAddNewPiece(spriteblock piece) {
		// check each of the four blocks
		for(int i = 0; i < Constblock.FOUR_BLOCKS; i++) {
			int blockX = piece.getBlockX(i);
			int blockY = piece.getBlockY(i);
			if(!this.checkBlockMove(blockX, blockY)) 
                        {
				return false;
			}
		}
		
		return true;
	}
	public void addNewPiece(spriteblock piece) {
		this.updatePieceBlocks(piece, Constblock.BLOCK_ACTIVE);
	}
	public boolean canMoveDown(spriteblock piece) {
		return this.canTranslatePiece(piece, 0, 1);
	}
	public boolean canMoveLeft(spriteblock piece) {
		return this.canTranslatePiece(piece, -1, 0);
	}
	public boolean canMoveRight(spriteblock piece) {
		return this.canTranslatePiece(piece, 1, 0);
	}
	private boolean canTranslatePiece(spriteblock piece, int dx, int dy) {
		
		// find each of the new blocks that would be occupied, check each one of them
		for(int i = 0; i < Constblock.FOUR_BLOCKS; i++) {
			int blockX = piece.getBlockX(i);
			int blockY = piece.getBlockY(i);
			
			int moveX = blockX + dx;
			int moveY = blockY + dy;
			
			if(!this.checkBlockMove(moveX, moveY)) {
				return false;
			}
		}
		
		return true;
	}
	public void moveDown(spriteblock piece) 
        {
		this.translatePiece(piece, 0, 1);
	}
	public void moveLeft(spriteblock piece) {
		this.translatePiece(piece, -1, 0);
	}
	public void moveRight(spriteblock piece) {
		this.translatePiece(piece, 1, 0);
	}
	public void translatePiece(spriteblock piece, int dx, int dy) {
		// clear the current position of the piece on the board
		this.updatePieceBlocks(piece, Constblock.BLOCK_EMPTY);
		
		// rotate the piece, this adjusts the state of the piece
		piece.translatePiece(dx, dy);

		// update the board with the new position of the piece
		this.updatePieceBlocks(piece, Constblock.BLOCK_ACTIVE);
	}
	
	public boolean canRotateLeft(spriteblock piece) {
		return this.canRotatePiece(piece,
				   piece.getBlockX(Constblock.PIVOT_INDEX), piece.getBlockY(Constblock.PIVOT_INDEX),
				   true);
	}
	
	public boolean canRotateRight(spriteblock piece) {
		return this.canRotatePiece(piece,
								   piece.getBlockX(Constblock.PIVOT_INDEX), piece.getBlockY(Constblock.PIVOT_INDEX),
								   false);
	}
	
	private boolean canRotatePiece(spriteblock piece, int pivotX, int pivotY, boolean rotateDirection) {
		
		int rotationType = piece.getRotationType();
		if(Constblock.ROTATION_TYPE_NONE == rotationType) {
			// piece can't rotate at all no matter what, this should be an O piece.
			return false;
		} else if(Constblock.ROTATION_TYPE_TOGGLE == rotationType) {
			// an I, S, or Z piece, can only has two flip positions
			rotateDirection = piece.getRotationToggle();
		} // else ROTATION_TYPE_FREE, just use the given rotate direction
			
		for(int i = 0; i < Constblock.FOUR_BLOCKS; i++) {
			int blockX = piece.getBlockX(i);
			int blockY = piece.getBlockY(i);
			
			// rotate left: swap the x and y, and flip sign of x
			// rotate right: swap the x and y, and flip sign of y 
			int dx = blockY - pivotY;
			int dy = blockX - pivotX;
		
			if(rotateDirection) {
				// rotate left
				dx *= -1;
			} else {
				// rotate right
				dy *= -1;
			}
			
			int rotateX = pivotX + dx;
			int rotateY = pivotY + dy;
			
			if(!this.checkBlockMove(rotateX, rotateY)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Rotate the piece left (counter-clockwise).
	 * @param piece the piece to rotate.
	 */
	public void rotateLeft(spriteblock piece) {
		this.rotatePiece(piece,
				   		 piece.getBlockX(Constblock.PIVOT_INDEX), piece.getBlockY(Constblock.PIVOT_INDEX),
						 true);
	}
	
	public void rotateRight(spriteblock piece) {
		this.rotatePiece(piece,
				   		 piece.getBlockX(Constblock.PIVOT_INDEX), piece.getBlockY(Constblock.PIVOT_INDEX),
						 false);
	}
	
	private void rotatePiece(spriteblock piece, int pivotX, int pivotY, boolean direction) {
		// clear the current position of the piece on the board
		this.updatePieceBlocks(piece, Constblock.BLOCK_EMPTY);
		
		// rotate the piece, this adjusts the state of the piece
		piece.rotate(pivotX, pivotY, direction);

		// update the board with the new position of the piece
		this.updatePieceBlocks(piece, Constblock.BLOCK_ACTIVE);
	}
}
