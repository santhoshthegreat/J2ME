public class spriteblock 
{
	private int[][] blocks = new int[Constblock.FOUR_BLOCKS][2];	// 2nd dim: [0] is x, [1] is y	
	private int pieceType;			// the type of the piece
	private int rotationType;		// the rotation type of the piece, should be one of defined constants, same until set as new piece
	private boolean rotationToggle;	// the current rotation position, changes with each rotation
	public void setAsNewPiece(int pieceType, int x, int y) 
        {
		switch(pieceType) 
                {
			case Constblock.I_PIECE:
				this.setAsNewIPiece(Constblock.START_X, Constblock.START_Y);
				break;
			case Constblock.O_PIECE:
				this.setAsNewOPiece(Constblock.START_X, Constblock.START_Y);
				break;
			case Constblock.T_PIECE:
				this.setAsNewTPiece(Constblock.START_X, Constblock.START_Y);
				break;
			case Constblock.S_PIECE:
				this.setAsNewSPiece(Constblock.START_X, Constblock.START_Y);
				break;
			case Constblock.Z_PIECE:
				this.setAsNewZPiece(Constblock.START_X, Constblock.START_Y);
				break;
			case Constblock.L_PIECE:
				this.setAsNewLPiece(Constblock.START_X, Constblock.START_Y);
				break;
			case Constblock.J_PIECE:
				this.setAsNewJPiece(Constblock.START_X, Constblock.START_Y);
		}
	}
	private void setAsNewIPiece(int x, int y) {
		this.pieceType = Constblock.I_PIECE;
		
		this.rotationType = Constblock.ROTATION_TYPE_TOGGLE;	// only has two rotation positions
		this.rotationToggle = true;					// only necessary to reset for consistent behavior with new pieces
		
		this.setBlockCoords(0, x - 1, y);		
		this.setBlockCoords(1, x    , y);
		this.setBlockCoords(2, x + 1, y);
		this.setBlockCoords(3, x + 2, y);
	}
	
	/**
	 * 	@#
	 * 	##
	 * 
	 * pivot only used for initial placement, O's can't rotate
	 * 
	 * @param x the x coordinate around which the new piece will start
	 * @param y the y coordinate around which the new piece will start
	 */
	private void setAsNewOPiece(int x, int y) {
		this.pieceType = Constblock.O_PIECE;
		
		this.rotationType = Constblock.ROTATION_TYPE_NONE;		// can't rotate at all
		
		this.setBlockCoords(0, x,     y);
		this.setBlockCoords(1, x + 1, y);
		this.setBlockCoords(2, x,     y + 1);
		this.setBlockCoords(3, x + 1, y + 1);
	}
	
	/**
	 *	#@#
	 * 	 #
	 * 
	 * @param x the x coordinate around which the new piece will start
	 * @param y the y coordinate around which the new piece will start
	 */
	private void setAsNewTPiece(int x, int y) {
		this.pieceType = Constblock.T_PIECE;
		
		this.rotationType = Constblock.ROTATION_TYPE_FREE;		// rotates freely, four possible positions
		
		this.setBlockCoords(0, x - 1, y);
		this.setBlockCoords(1, x,     y);
		this.setBlockCoords(2, x + 1, y);
		this.setBlockCoords(3, x,     y + 1);
	}
	
	/**
	 *	 @#
	 *  ##
	 * 
	 * @param x the x coordinate around which the new piece will start
	 * @param y the y coordinate around which the new piece will start
	 */
	private void setAsNewSPiece(int x, int y) {
		this.pieceType = Constblock.S_PIECE;
		
		this.rotationType = Constblock.ROTATION_TYPE_TOGGLE;	// only has two rotation positions
		this.rotationToggle = true;					// only necessary to reset for consistent behavior with new pieces
		
		this.setBlockCoords(0, x + 1, y);
		this.setBlockCoords(1, x,     y);
		this.setBlockCoords(2, x,	  y + 1);
		this.setBlockCoords(3, x - 1, y + 1);
	}
	
	/**
	 *	#@  
	 *   ##
	 * 
	 * @param x the x coordinate around which the new piece will start
	 * @param y the y coordinate around which the new piece will start
	 */
	private void setAsNewZPiece(int x, int y) {
		this.pieceType = Constblock.Z_PIECE;
		
		this.rotationType = Constblock.ROTATION_TYPE_TOGGLE;	// only has two rotation positions
		this.rotationToggle = true;					// only necessary to reset for consistent behavior with new pieces
		
		this.setBlockCoords(0, x - 1, y);
		this.setBlockCoords(1, x,     y);
		this.setBlockCoords(2, x,     y + 1);
		this.setBlockCoords(3, x + 1, y + 1);
	}
	

	private void setAsNewLPiece(int x, int y) {
		this.pieceType = Constblock.L_PIECE;
		
		this.rotationType = Constblock.ROTATION_TYPE_FREE;		// rotates freely, four possible positions
		
		this.setBlockCoords(0, x - 1, y);
		this.setBlockCoords(1, x,     y);
		this.setBlockCoords(2, x + 1, y);
		this.setBlockCoords(3, x - 1, y + 1);
	}
	private void setAsNewJPiece(int x, int y) {
		this.pieceType = Constblock.J_PIECE;
		
		this.rotationType = Constblock.ROTATION_TYPE_FREE;		// rotates freely, four possible positions
		
		this.setBlockCoords(0, x - 1, y);
		this.setBlockCoords(1, x,     y);
		this.setBlockCoords(2, x + 1, y);
		this.setBlockCoords(3, x + 1, y + 1);
	}

	public int getBlockX(int blockIndex) {
		return this.blocks[blockIndex][0];
	}

	public int getBlockY(int blockIndex) {
		return this.blocks[blockIndex][1];	
	}

	public void setBlockCoords(int blockIndex, int x, int y) {
		this.blocks[blockIndex][0] = x;	// x is index 0 in the 2nd dim
		this.blocks[blockIndex][1] = y;	// y is index 1 in the 2nd dim
	}
	
	public void translatePiece(int dx, int dy) {
		
		// iterate over the four blocks, translate each
		for(int i = 0; i < Constblock.FOUR_BLOCKS; i++) {
			int moveX = this.getBlockX(i) + dx;
			int moveY = this.getBlockY(i) + dy;
		
			this.setBlockCoords(i, moveX, moveY);
		}
	}
	
	public void rotate(int pivotX, int pivotY, boolean rotateDirection) {
		
		if(Constblock.ROTATION_TYPE_TOGGLE == this.rotationType) {
			// if this is a toggling rotation piece (I, S, or Z), we just ignore
			// the requested rotation direction, and just toggle back and forth
			rotateDirection = this.rotationToggle;
			this.rotationToggle = !this.rotationToggle; // toggle should be after use, so it will match the get in canRotate
		}
		
		// iterate over the four blocks, rotate each
		for(int i = 0; i < Constblock.FOUR_BLOCKS; i++) {
			int blockX = this.getBlockX(i);
			int blockY = this.getBlockY(i);
			
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
			
			this.setBlockCoords(i, rotateX, rotateY);
		}
	}
	
	/**
	 * @return the currently set type of this piece, should be a constant defined in constants
	 */
	public int getPieceType() {
		return this.pieceType;
	}

	/**
	 * @return the rotation type of the current piece, should be a constant defined in constants
	 */
	public int getRotationType() {
		return this.rotationType;
	}
	
	/**
	 * @return get the current rotation toggle, useful when piece is one of those with only two rotations, (I, S, Z)
	 */
	public boolean getRotationToggle() {
		return this.rotationToggle;
	}
}
