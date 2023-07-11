package chess.pieces;

import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PieceTest {
    Piece whitePawn = PieceFactory.createPiece(Color.WHITE, Type.PAWN);
    Piece blackPawn = PieceFactory.createPiece(Color.BLACK, Type.PAWN);
    Piece whiteKing = PieceFactory.createPiece(Color.WHITE, Type.KING);
    Piece blackBishop = PieceFactory.createPiece(Color.BLACK, Type.BISHOP);
    Piece blank = PieceFactory.createBlank();

    @Test
    @DisplayName("각 기물 종류/색상에 맞는 representation을 리턴해야 한다.")
    void getRepresentation() {
        // given

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

        // when
        boolean whitePawnIsPawn = whitePawn.isPawn();
        boolean blackPawnIsPawn = blackPawn.isPawn();
        boolean blankIsPawn = blank.isPawn();
        boolean whiteKingIsPawn = whiteKing.isPawn();
        boolean blackBishopIsPawn = blackBishop.isPawn();

        // then
        assertTrue(whitePawnIsPawn);
        assertTrue(blackPawnIsPawn);
        assertFalse(blankIsPawn);
        assertFalse(whiteKingIsPawn);
        assertFalse(blackBishopIsPawn);
    }

    @Test
    @DisplayName("해당 color, type을 갖는 Piece인지 여부를 반환해야 한다.")
    void checkColorAndType() {
        // given
        Color color = Color.WHITE;
        Type type = Type.PAWN;

        // when
        boolean checkWhitePawn = whitePawn.checkColorAndType(color, type);
        boolean checkBlackPawn = blackPawn.checkColorAndType(color, type);
        boolean checkBlackBishop = blackBishop.checkColorAndType(color, type);

        // then
        assertTrue(checkWhitePawn);
        assertFalse(checkBlackPawn);
        assertFalse(checkBlackBishop);
    }

    @Test
    @DisplayName("해당 color를 갖는 Piece인지 여부를 반환해야 한다.")
    void checkColor() {
        // given
        Color color = Color.WHITE;

        // when
        boolean checkWhitePawn = whitePawn.checkColor(color);
        boolean checkBlackPawn = blackPawn.checkColor(color);
        boolean checkWhiteKing = whiteKing.checkColor(color);
        boolean checkBlackBishop = blackBishop.checkColor(color);

        // then
        assertTrue(checkWhitePawn);
        assertFalse(checkBlackPawn);
        assertTrue(checkWhiteKing);
        assertFalse(checkBlackBishop);
    }
}
