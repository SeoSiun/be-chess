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

class BishopTest {
    private Board board;
    private ChessGame chessGame;
    private final Piece blank = PieceFactory.createBlank();

    @BeforeEach
    void setup() {
        board = new Board();
        chessGame = new ChessGame();
    }

    /**
     * check Bishop's movement
     */
    @Test
    @DisplayName("Bishop은 대각선 방향이라면 몇 칸이든 움직일 수 있다")
    void moveBishop() {
        // given
        Piece whiteBishop = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.BISHOP);
        board.initializeEmpty();
        board.move(Position.from("f1"), whiteBishop);
        String[] positions = {"f1", "g2", "d5", "b3", "d1"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteBishop, positions[index], positions[index + 1], Piece.Color.WHITE));
    }

    @Test
    @DisplayName("Bishop이 대각선이 아닌 방향으로 움직이면 InvalidTargetPositionException이 발생한다.")
    void moveBishopNonDiagonal() {
        // given
        Piece whiteBishop = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.BISHOP);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteBishop);

        assertThrows(InvalidTargetPositionException.class, () -> move("d4", "d8"));
        assertThrows(InvalidTargetPositionException.class, () -> move( "d4", "a4"));
        assertThrows(InvalidTargetPositionException.class, () -> move("d4", "g6"));
    }

    private void move(String sourcePosition, String targetPosition) {
        chessGame.move(board, sourcePosition, targetPosition, board.findPiece(sourcePosition).getColor());
    }

    private void verifyMovement(Piece pieceToMove, String sourcePosition, String targetPosition, Piece.Color color) {
        // when
        chessGame.move(board, sourcePosition, targetPosition, color);

        // then
        assertEquals(pieceToMove, board.findPiece(targetPosition));
        assertEquals(blank, board.findPiece(sourcePosition));
    }
}
