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

class RookTest {
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
        Piece whiteRook = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.ROOK);
        board.initializeEmpty();
        board.move(Position.from("a1"), whiteRook);
        String[] positions = {"a1", "a4", "e4", "e3", "c3"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteRook, positions[index], positions[index + 1], Piece.Color.WHITE));
    }

    @Test
    @DisplayName("Rook이 수직/수평이 아닌 방향으로 움직이면 예외가 발생한다.")
    void moveRookInvalidDirection() {
        // given
        Piece whiteRook = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.ROOK);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteRook);

        verifyException("d4", "a7", RecursivePiece.INVALID_TARGET_POSITION);
        verifyException("d4", "g1", RecursivePiece.INVALID_TARGET_POSITION);
        verifyException("d4", "g6", RecursivePiece.INVALID_TARGET_POSITION);
    }

    @Test
    @DisplayName("Rook이 이동하는 경로에 다른 기물이 있으면 UnreachableWithObstacleException이 발생해야 한다.")
    void moveRookWithObstacle() {
        // given
        Piece whiteRook = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.ROOK);
        board.initializeEmpty();
        board.move(Position.from("d1"), whiteRook);
        board.move(Position.from("d3"), PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.PAWN));

        // when & then
        verifyException( "d1", "d5", RecursivePiece.UNREACHABLE_BY_OBSTACLE);
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
