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
public final class Pawn extends Piece {
	/**
	 * @param position position of the piece in the BitSet (chess board)
	 * @param color piece's color
	 */
	public Pawn(byte position, Color color) {
		this.position = position;
		this.calculateMovable();
		this.color = color;
	}

	@Override
	public void movePiece(byte newPosition, boolean validate) {
		if(validate)
			this.validateMove(newPosition);
		
		if(newPosition - this.position == ClassicChess.indexGenerator(2, 0))
			board.setEnPassantCandidate(this);
		
		this.hasMoved = true;
		this.position = newPosition;
		this.calculateMovable();
	}

	@Override
	public void calculateMovable() {
		this.movablePositions = new BitSet();
		Player otherPlayer = this.color == Color.WHITE ? this.board.getWhitePlayer() : this.board.getBlackPlayer();
		var otherPlayersPieces = otherPlayer.getPiecePositions();
		
		// Moving forward
		byte candidateForward1 = (byte) (this.color == Color.WHITE? this.position + ClassicChess.indexGenerator(1, 0) : this.position + ClassicChess.indexGenerator(-1, 0));
		if(ClassicChess.isWithinBoard(candidateForward1) && !this.board.occupiedPositions().get(candidateForward1))
			this.movablePositions.set(candidateForward1);
		
		if(!this.hasMoved) {
			byte candidateForward2 = (byte) (this.color == Color.WHITE? this.position + ClassicChess.indexGenerator(2, 0) : this.position + ClassicChess.indexGenerator(-2, 0));
			if(ClassicChess.isWithinBoard(candidateForward2) && !this.board.occupiedPositions().get(candidateForward2))
				this.movablePositions.set(candidateForward2);
		}
		
		// Capturing pieces
		byte candidateCapture1 = (byte) (this.color == Color.WHITE? this.position + ClassicChess.indexGenerator(1, -1) : this.position + ClassicChess.indexGenerator(-1, -1));
		byte candidateCapture2 = (byte) (this.color == Color.WHITE? this.position + ClassicChess.indexGenerator(1, 1) : this.position + ClassicChess.indexGenerator(-1, 1));
		
		if(otherPlayersPieces.get(candidateCapture1))
			this.movablePositions.set(candidateCapture1);
		
		if(otherPlayersPieces.get(candidateCapture2))
			this.movablePositions.set(candidateCapture2);
		
		Piece enPassantCandidate = this.board.getEnPassantCandidate();
		
		if(enPassantCandidate != null && enPassantCandidate.position == position + ClassicChess.indexGenerator(0, -1))
			this.movablePositions.set(candidateCapture1);
		
		if(enPassantCandidate != null && enPassantCandidate.position == position + ClassicChess.indexGenerator(0, 1))
			this.movablePositions.set(candidateCapture2);
	}
}
