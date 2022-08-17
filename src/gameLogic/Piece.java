/**
 * 
 */
package gameLogic;

import java.util.BitSet;

import gameLogic.ClassicChess.Color;

/**
 * @author suspect
 *
 */
public abstract class Piece {
	protected byte position;
	protected boolean hasMoved = false;
	protected BitSet movablePositions;
	protected ClassicChess board;
	protected Color color;
	
	/**
	 * Updates the piece's information when it moves.
	 * @param newPosition where the piece moves to.
	 * @param validate whether to validate if the piece moves that way or not.
	 */
	public abstract void movePiece(byte newPosition, boolean validate);
	
	/**
	 *  Updates piece's information when it moves
	 */
	public void movePiece(byte newPosition) {
		this.movePiece(newPosition, true);
	}
	
	/**
	 *  Validates move.
	 */
	protected boolean validateMove(byte candidatePosition) {
		return movablePositions.get(candidatePosition);
	}
	
	/**
	 * This should update position and movablePositions.
	 */
	protected abstract void calculateMovable();
}
