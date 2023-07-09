package chess.pieces;

import chess.pieces.Piece.Type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PieceTest {
    @Test
    @DisplayName("각 기물 종류/색별 팩토리 메소드는 해당하는 색, 타입을 갖는 Piece를 생성해야 한다.")
    void create() {
        // given

        // when
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        Piece whiteKnight = Piece.createWhiteKnight();
        Piece blackKnight = Piece.createBlackKnight();
        Piece whiteRook = Piece.createWhiteRook();
        Piece blackRook = Piece.createBlackRook();
        Piece whiteBishop = Piece.createWhiteBishop();
        Piece blackBishop = Piece.createBlackBishop();
        Piece whiteQueen = Piece.createWhiteQueen();
        Piece blackQueen = Piece.createBlackQueen();
        Piece whiteKing = Piece.createWhiteKing();
        Piece blackKing = Piece.createBlackKing();

        // Then
        verifyPiece(whitePawn, blackPawn, Type.PAWN);
        verifyPiece(whiteKnight, blackKnight, Type.KNIGHT);
        verifyPiece(whiteRook, blackRook, Type.ROOK);
        verifyPiece(whiteBishop, blackBishop, Type.BISHOP);
        verifyPiece(whiteQueen, blackQueen, Type.QUEEN);
        verifyPiece(whiteKing, blackKing, Type.KING);
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
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
        Piece blank = Piece.createBlank();

        // then
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    @Test
    @DisplayName("각 기물 종류/색상에 맞는 representation을 리턴해야 한다.")
    void getRepresentation() {
        // given
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        Piece whiteKing = Piece.createWhiteKing();
        Piece blackBishop = Piece.createBlackBishop();

        // when
        char whitePawnRepresentation = whitePawn.getRepresentation();
        char blackPawnRepresentation = blackPawn.getRepresentation();
        char whiteKingRepresentation = whiteKing.getRepresentation();
        char blackBishopRepresentation = blackBishop.getRepresentation();

        // then
        assertEquals(Type.PAWN.getWhiteRepresentation(), whitePawnRepresentation);
        assertEquals(Type.PAWN.getBlackRepresentation(), blackPawnRepresentation);
        assertEquals(Type.KING.getWhiteRepresentation(), whiteKingRepresentation);
        assertEquals(Type.BISHOP.getBlackRepresentation(), blackBishopRepresentation);
    }

    @Test
    @DisplayName("Color가 BLACK인 Piece인지 여부를 반환해야 한다.")
    void isBlack() {
        // given
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();

        // when
        boolean whitePawnIsBlack = whitePawn.isBlack();
        boolean blackPawnIsBlack = blackPawn.isBlack();

        // then
        assertFalse(whitePawnIsBlack);
        assertTrue(blackPawnIsBlack);
    }

    @Test
    @DisplayName("Color가 WHITE인 Piece인지 여부를 반환해야 한다.")
    void isWhite() {
        // given
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();

        // when
        boolean whitePawnIsWhite = whitePawn.isWhite();
        boolean blackPawnIsWhite = blackPawn.isWhite();

        // then
        assertTrue(whitePawnIsWhite);
        assertFalse(blackPawnIsWhite);
    }

    @Test
    @DisplayName("Type이 NO_PIECE인지 여부를 반환해야 한다.")
    void isBlank() {
        // given
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        Piece blank = Piece.createBlank();

        // when
        boolean whitePawnIsBlank = whitePawn.isBlank();
        boolean blackPawnIsBlank = blackPawn.isBlank();
        boolean blankIsBlank = blank.isBlank();

        // then
        assertFalse(whitePawnIsBlank);
        assertFalse(blackPawnIsBlank);
        assertTrue(blankIsBlank);
    }

    @Test
    @DisplayName("Type이 PAWN인지 여부를 반환해야 한다.")
    void isPawn() {
        // given
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        Piece blank = Piece.createBlank();
        Piece whiteKing = Piece.createWhiteKing();
        Piece blackKnight = Piece.createBlackKnight();

        // when
        boolean whitePawnIsPawn = whitePawn.isPawn();
        boolean blackPawnIsPawn = blackPawn.isPawn();
        boolean blankIsPawn = blank.isPawn();
        boolean whiteKingIsPawn = whiteKing.isPawn();
        boolean blackKnightIsPawn = blackKnight.isPawn();

        // then
        assertTrue(whitePawnIsPawn);
        assertTrue(blackPawnIsPawn);
        assertFalse(blankIsPawn);
        assertFalse(whiteKingIsPawn);
        assertFalse(blackKnightIsPawn);
    }

    @Test
    @DisplayName("해당 color, type을 갖는 Piece인지 여부를 반환해야 한다.")
    void checkColorAndType() {
        // given
        Piece.Color color = Piece.Color.WHITE;
        Type type = Type.PAWN;

        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        Piece blackKnight = Piece.createBlackKnight();

        // when
        boolean checkWhitePawn = whitePawn.checkColorAndType(color, type);
        boolean checkBlackPawn = blackPawn.checkColorAndType(color, type);
        boolean checkBlackKnight = blackKnight.checkColorAndType(color, type);

        // then
        assertTrue(checkWhitePawn);
        assertFalse(checkBlackPawn);
        assertFalse(checkBlackKnight);
    }

    @Test
    @DisplayName("해당 color를 갖는 Piece인지 여부를 반환해야 한다.")
    void checkColor() {
        // given
        Piece.Color color = Piece.Color.WHITE;

        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        Piece whiteKnight = Piece.createWhiteKnight();
        Piece blackKnight = Piece.createBlackKnight();

        // when
        boolean checkWhitePawn = whitePawn.checkColor(color);
        boolean checkBlackPawn = blackPawn.checkColor(color);
        boolean checkWhiteKnight = whiteKnight.checkColor(color);
        boolean checkBlackKnight = blackKnight.checkColor(color);

        // then
        assertTrue(checkWhitePawn);
        assertFalse(checkBlackPawn);
        assertTrue(checkWhiteKnight);
        assertFalse(checkBlackKnight);
    }
}
