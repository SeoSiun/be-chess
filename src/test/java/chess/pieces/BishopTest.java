package chess.pieces;

import chess.Board;
import chess.ChessGame;
import chess.Position;
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
        Piece whiteBishop = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.BISHOP);
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
        Piece whiteBishop = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.BISHOP);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteBishop);

        verifyException("d4", "d8", RecursivePiece.INVALID_TARGET_POSITION);
        verifyException("d4", "a4", RecursivePiece.INVALID_TARGET_POSITION);
        verifyException("d4", "g6", RecursivePiece.INVALID_TARGET_POSITION);
    }

    @Test
    @DisplayName("target까지 가는 길에 장애물(다른 기물)이 있다면 예외가 발생한다.")
    void moveUnreachableTarget() {
        // given
        board.initializeEmpty();
        String sourcePosition = "c1";
        String targetPosition = "e3";

        board.move(Position.from(sourcePosition), PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.BISHOP));
        board.move(Position.from("d2"), PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.PAWN));

        // when & then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> move(sourcePosition, targetPosition));
        assertEquals(RecursivePiece.UNREACHABLE_BY_OBSTACLE, exception.getMessage());
    }

    private void verifyException(String source, String target, String expectedMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> move(source, target));
        assertEquals(expectedMessage, exception.getMessage());
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
