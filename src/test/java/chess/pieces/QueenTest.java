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

class QueenTest {
    private Board board;
    private ChessGame chessGame;
    private final Piece blank = PieceFactory.createBlank();

    @BeforeEach
    void setup() {
        board = new Board();
        chessGame = new ChessGame();
    }

    /**
     * check Queen's Movement
     */
    @Test
    @DisplayName("Queen은 대각선, 수직, 수평 방향으로 몇 칸이든 이동할 수 있다.")
    void moveQueen() {
        // given
        Piece whiteQueen = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN);
        board.initializeEmpty();
        board.move(Position.from("d1"), whiteQueen);
        String[] positions = {"d1", "d5", "g8", "c8", "a6", "a5", "e1", "c1", "a3"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteQueen, positions[index], positions[index + 1], Piece.Color.WHITE));

    }

    @Test
    @DisplayName("Queen이 체스판을 벗어나는 위치로 이동하면 PositionOutOfRangeException이 발생해야 한다.")
    void moveQueenOutOfRange() {
        // given
        Piece whiteQueen = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteQueen);

        // when & then
        verifyException( "e5", "e9", Position.POSITION_OUT_OF_RANGE);
    }

    @Test
    @DisplayName("Queen이 대각선, 수직, 수평 방향이 아닌 방향으로 이동하면 예외가 발생한다.")
    void moveQueenRandomDirection() {
        // given
        Piece whiteQueen = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteQueen);

        // when & then
        verifyException("e5", "f8", RecursivePiece.INVALID_TARGET_POSITION);
        verifyException("e5", "a4", RecursivePiece.INVALID_TARGET_POSITION);
        verifyException("e5", "h1", RecursivePiece.INVALID_TARGET_POSITION);
        verifyException("e5", "b7", RecursivePiece.INVALID_TARGET_POSITION);
    }

    @Test
    @DisplayName("Queen이 이동하는 경로에 다른 기물이 있으면 UnreachableWithObstacleException이 발생해야 한다.")
    void moveQueenWithObstacle() {
        // given
        Piece whiteQueen = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN);
        board.initializeEmpty();
        board.move(Position.from("d1"), whiteQueen);
        board.move(Position.from("d3"), PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.PAWN));

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
