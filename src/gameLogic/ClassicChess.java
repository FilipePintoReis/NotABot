/**
 * 
 */
package gameLogic;

import java.util.BitSet;

/**
 * @author suspect
 *
 */
public final class ClassicChess {
	/*
	 * This variable has to be set to null after each play
	 */
	private Piece enPassantCandidate;
	private Player whitePlayer = new WhitePlayer();
	private Player blackPlayer = new BlackPlayer();
	private final static byte BOARD_SIZE = 64;
	private final static byte Y_AXIS_UNIT = 8;
	private final static byte X_AXIS_UNIT = 1;
	
	public static enum Color {
		WHITE, BLACK
	}
	/**
	 * @return the enPassantCandidate
	 */
	public Piece getEnPassantCandidate() {
		return enPassantCandidate;
	}
	/**
	 * @param enPassantCandidate the enPassantCandidate to set
	 */
	public void setEnPassantCandidate(Piece enPassantCandidate) {
		this.enPassantCandidate = enPassantCandidate;
	}
	/**
	 * @return the whitePlayer
	 */
	public Player getWhitePlayer() {
		return whitePlayer;
	}
	/**
	 * @param whitePlayer the whitePlayer to set
	 */
	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}
	/**
	 * @return the blackPlayer
	 */
	public Player getBlackPlayer() {
		return blackPlayer;
	}
	/**
	 * @param blackPlayer the blackPlayer to set
	 */
	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}
	/**
	 * @return the board positions that are occupied
	 */
	public BitSet occupiedPositions() {
		var occupiedPositions = new BitSet();
		occupiedPositions.and(whitePlayer.getPiecePositions());
		occupiedPositions.and(blackPlayer.getPiecePositions());
		return occupiedPositions;
	}
	/**
	 * Ensures within board bounds
	 */
	public static boolean isWithinBoard(byte position) {
		return position >= 0 && position < BOARD_SIZE;
	}
	/**
	 * Generates index to move assuming a one-dimensional chess board
	 */
	public static byte indexGenerator(int yAxis, int xAxis) throws IllegalArgumentException{
		if(Math.abs(xAxis) >= Y_AXIS_UNIT)
			throw new IllegalArgumentException("xAxis must be smaller than board width.");
		
		return (byte) (yAxis * Y_AXIS_UNIT + xAxis * X_AXIS_UNIT);
	}
}
