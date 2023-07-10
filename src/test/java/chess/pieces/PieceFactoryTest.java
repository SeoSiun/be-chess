package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceFactoryTest {
    @Test
    @DisplayName("각 기물 종류/색별 팩토리 메소드는 해당하는 색, 타입을 갖는 Piece를 생성해야 한다.")
    void create() {
        // given

        // when
        Piece whitePawn = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.PAWN);
        Piece blackPawn = PieceFactory.createPiece(Piece.Color.BLACK, Piece.Type.PAWN);
        Piece whiteKnight = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT);
        Piece blackKnight = PieceFactory.createPiece(Piece.Color.BLACK, Piece.Type.KNIGHT);
        Piece whiteRook = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.ROOK);
        Piece blackRook = PieceFactory.createPiece(Piece.Color.BLACK, Piece.Type.ROOK);
        Piece whiteBishop = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.BISHOP);
        Piece blackBishop = PieceFactory.createPiece(Piece.Color.BLACK, Piece.Type.BISHOP);
        Piece whiteQueen = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN);
        Piece blackQueen = PieceFactory.createPiece(Piece.Color.BLACK, Piece.Type.QUEEN);
        Piece whiteKing = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.KING);
        Piece blackKing = PieceFactory.createPiece(Piece.Color.BLACK, Piece.Type.KING);

        // Then
        verifyPiece(whitePawn, blackPawn, Piece.Type.PAWN);
        verifyPiece(whiteKnight, blackKnight, Piece.Type.KNIGHT);
        verifyPiece(whiteRook, blackRook, Piece.Type.ROOK);
        verifyPiece(whiteBishop, blackBishop, Piece.Type.BISHOP);
        verifyPiece(whiteQueen, blackQueen, Piece.Type.QUEEN);
        verifyPiece(whiteKing, blackKing, Piece.Type.KING);
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Piece.Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

    @Test
    @DisplayName("빈칸에 해당하는 Piece를 생성해야한다.")
    void createBlank() {
        // given

        // when
        Piece blank = PieceFactory.createBlank();

        // then
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Piece.Type.NO_PIECE, blank.getType());
    }
}
