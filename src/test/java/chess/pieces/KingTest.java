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

class KingTest {
    private Board board;
    private ChessGame chessGame;
    private final Piece blank = PieceFactory.createBlank();

    @BeforeEach
    void setup() {
        board = new Board();
        chessGame = new ChessGame();
    }

    /**
     * check King's movement
     */
    @Test
    @DisplayName("King은 어느방향이든 1칸 움직일 수 있다.")
    void moveKing() {
        // given
        Piece whiteKing = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.KING);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteKing);
        String[] positions = {"e5", "e6", "f7", "g7", "h6", "h5", "g4", "f4", "e5"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteKing, positions[index], positions[index + 1], Piece.Color.WHITE));
    }

    @Test
    @DisplayName("King이 체스판을 벗어나는 위치로 이동하면 PositionOutOfRangeException이 발생해야 한다.")
    void moveKingOutOfRange() {
        // given
        Piece whiteKing = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.KING);
        board.initializeEmpty();
        board.move(Position.from("e8"), whiteKing);

        // when & then
        verifyException("e8", "e9",Position.POSITION_OUT_OF_RANGE);
    }

    @Test
    @DisplayName("King이 1칸 이상 움직이면 InvalidTargetPositionException이 발생해야 한다.")
    void moveKingMultipleStep() {
        // given
        Piece whiteKing = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.KING);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteKing);

        // when & then
        verifyException("e5", "e2", RecursivePiece.INVALID_TARGET_POSITION);
    }

    @Test
    @DisplayName("King이 이동하려는 위치에 같은 색의 기물이 있으면 TargetSameColorException이 발생해야 한다.")
    void moveKingWithObstacle() {
        // given
        Piece whiteKing = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.KING);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteKing);
        board.move(Position.from("e4"), PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.PAWN));

        // when & then
        verifyException("e5", "e4", ChessGame.TARGET_IS_SAME_COLOR);
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
