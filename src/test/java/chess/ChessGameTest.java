package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.pieces.PieceFactory;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChessGameTest {
    private Board board;
    private ChessView chessView;
    private ChessGame chessGame;

    private final Piece blank = PieceFactory.createBlank();


    @BeforeEach
    void setup() {
        board = new Board();
        chessView = new ChessView();
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("source 위치에 있는 Piece가 target 위치로 옮겨져야 한다.")
    void moveFromSourceToTarget() throws Exception {
        // given
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";

        // when
        chessGame.move(board, sourcePosition, targetPosition);

        // then
        assertEquals(PieceFactory.createBlank(), board.findPiece(sourcePosition));
        assertEquals(PieceFactory.createPiece(Color.WHITE, Type.PAWN), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("sourcePosition에 기물이 존재하지 않는 경우 NoPieceInSourceException을 throw한다.")
    void moveNoPiece() {
        // given
        board.initialize();
        String sourcePosition = "a5";
        String targetPosition = "a2";

        // when & then
        assertThrows(NoPieceInSourceException.class, () -> chessGame.move(board, sourcePosition, targetPosition));
    }

    @Test
    @DisplayName("같은 색상의 기물이 있는 위치로 움직이는 경우 TargetSameColorException을 throw한다.")
    void moveWithSameColor() {
        // given
        board.initialize();
        String sourcePosition = "a1";
        String targetPosition = "a2";

        // when & then
        assertThrows(TargetSameColorException.class, () -> chessGame.move(board, sourcePosition, targetPosition));
    }

    @Test
    @DisplayName("해당 기물이 움직일 수 없는 위치로 움직이는 경우 InvalidTargetPositionException을 throw한다.")
    void moveInvalidDirection() {
        // given
        board.initialize();
        String sourcePosition = "a2";
        String targetPosition = "b4";

        // when & then
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move(board, sourcePosition, targetPosition));
    }

    @Test
    @DisplayName("source와 target이 같은 경우 TargetSameAsSourceException을 throw한다.")
    void moveSamePosition() {
        // given
        board.initialize();
        String sourcePosition = "a2";
        String targetPosition = "a2";

        // when & then
        assertThrows(TargetSameAsSourceException.class, () -> chessGame.move(board, sourcePosition, targetPosition));
    }
}
