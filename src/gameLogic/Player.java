/**
 * 
 */
package gameLogic;

import java.util.ArrayList;
import java.util.BitSet;

import gameLogic.ClassicChess.Color;

/**
 * @author suspect
 *
 */
public abstract class Player {
	protected ArrayList<Piece> pieces;
	protected BitSet piecePositions;
	protected Color color;
	
	/**
	 * possibleMoves returns an ArrayList of short[2]
	 * where the 0th index refers to the starting position and
	 * the 1th index refers to the candidate position.
	 */
	public abstract ArrayList<short[]> possibleMoves();
	
	public void createFirstPieces() {
		
	}
	
	public BitSet getPiecePositions() {
		var playerPositions = new BitSet();
		
		for(var piece : pieces)
			playerPositions.set(piece.position);
		
		return playerPositions;
	}
}
