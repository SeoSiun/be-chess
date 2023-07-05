package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTest {
    @Test
    @DisplayName("모든 종류의 기물 생성 및 representation 체크")
    public void create_pieces() {
        verifyPiece(Piece.createWhitePawn(), Color.WHITE, Type.PAWN.getRepresentation());
        verifyPiece(Piece.createBlackPawn(), Color.BLACK, Character.toUpperCase(Type.PAWN.getRepresentation()));

        verifyPiece(Piece.createWhiteKnight(), Color.WHITE, Type.KNIGHT.getRepresentation());
        verifyPiece(Piece.createBlackKnight(), Color.BLACK, Character.toUpperCase(Type.KNIGHT.getRepresentation()));

        verifyPiece(Piece.createWhiteRook(), Color.WHITE, Type.ROOK.getRepresentation());
        verifyPiece(Piece.createBlackRook(), Color.BLACK, Character.toUpperCase(Type.ROOK.getRepresentation()));

        verifyPiece(Piece.createWhiteBishop(), Color.WHITE, Type.BISHOP.getRepresentation());
        verifyPiece(Piece.createBlackBishop(), Color.BLACK, Character.toUpperCase(Type.BISHOP.getRepresentation()));

        verifyPiece(Piece.createWhiteQueen(), Color.WHITE, Type.QUEEN.getRepresentation());
        verifyPiece(Piece.createBlackQueen(), Color.BLACK, Character.toUpperCase(Type.QUEEN.getRepresentation()));

        verifyPiece(Piece.createWhiteKing(), Color.WHITE, Type.KING.getRepresentation());
        verifyPiece(Piece.createBlackKing(), Color.BLACK, Character.toUpperCase(Type.KING.getRepresentation()));
    }

    private void verifyPiece(final Piece piece, final Color color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}
