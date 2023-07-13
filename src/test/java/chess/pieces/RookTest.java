package chess.pieces;

import chess.Board;
import chess.ChessGame;
import chess.Position;
import exceptions.InvalidTargetPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RookTest {
    private Board board;
    private ChessGame chessGame;
    private final Piece blank = PieceFactory.createBlank();

    @BeforeEach
    void setup() {
        board = new Board();
        chessGame = new ChessGame();
    }

    /**
     * check Rook's movement
     */
    @Test
    @DisplayName("Rook은 수평/수직 방향이라면 몇 칸이든 움직일 수 있다")
    void moveRook() {
        // given
        Piece whiteRook = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.ROOK);
        board.initializeEmpty();
        board.move(Position.from("a1"), whiteRook);
        String[] positions = {"a1", "a4", "e4", "e3", "c3"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteRook, positions[index], positions[index + 1]));
    }

    @Test
    @DisplayName("Rook이 수직/수평이 아닌 방향으로 움직이면 InvalidTargetPositionException이 발생한다.")
    void moveRookInvalidDirection() {
        // given
        Piece whiteRook = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.ROOK);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteRook);

        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move(board, "d4", "a7"));
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move(board, "d4", "g1"));
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move(board, "d4", "g6"));
    }

    private void verifyMovement(Piece pieceToMove, String sourcePosition, String targetPosition) {
        // when
        chessGame.move(board, sourcePosition, targetPosition);

        // then
        assertEquals(pieceToMove, board.findPiece(targetPosition));
        assertEquals(blank, board.findPiece(sourcePosition));
    }
}
