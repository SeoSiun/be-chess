package chess.pieces;

import chess.Board;
import chess.ChessGame;
import chess.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnTest {
    private Board board;
    private ChessGame chessGame;
    private final Piece blank = PieceFactory.createBlank();

    @BeforeEach
    void setup() {
        board = new Board();
        chessGame = new ChessGame();
    }

    /**
     * check pawn's movement
     */
    @Test
    @DisplayName("Pawn은 첫 번째 이동에는 앞으로 두 칸 또는 한 칸 움직일 수 있다.")
    void movePawnFirst() {
        // given
        Piece whitePawnForOneStep = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.PAWN);
        Piece whitePawnForTwoStep = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.PAWN);
        board.initializeEmpty();
        board.move(Position.from("b2"), whitePawnForOneStep);
        board.move(Position.from("c2"), whitePawnForTwoStep);

        // when & then
        verifyMovement(whitePawnForOneStep, "b2", "b3", Piece.Color.WHITE);
        verifyMovement(whitePawnForTwoStep, "c2", "c4", Piece.Color.WHITE);
    }

    @Test
    @DisplayName("Pawn은 두 번째 이동부터는 앞으로 한 칸만 움직일 수 있다.")
    void movePawnNonFirst() {
        // given
        Piece whitePawn = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.PAWN);
        board.initializeEmpty();
        board.move(Position.from("b2"), whitePawn);
        move("b2", "b4");

        // when & then
        verifyMovement(whitePawn, "b4", "b5", Piece.Color.WHITE);
        verifyException("b5", "b7", RecursivePiece.INVALID_TARGET_POSITION);
    }

    @Test
    @DisplayName("Pawn은 상대 편 기물이 있을 때만 대각선으로 이동할 수 있다.")
    void movePawnDiagonal() {
        // given
        Piece whitePawn = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.PAWN);
        Piece blackPawn = PieceFactory.createPieceByColorAndType(Piece.Color.BLACK, Piece.Type.PAWN);

        board.initializeEmpty();
        board.move(Position.from("b2"), whitePawn);
        board.move(Position.from("c3"), blackPawn);

        // when & then
        verifyMovement(whitePawn, "b2", "c3", Piece.Color.WHITE);
        verifyException("c3", "d4", Pawn.PAWN_MOVE_DIAGONAL_WITHOUT_ENEMY);
    }

    @Test
    @DisplayName("Pawn이 뒤로 움직이면 예외가 발생한다.")
    void movePawnBackward() {
        // given
        Piece whitePawn = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.PAWN);

        board.initializeEmpty();
        board.move(Position.from("e4"), whitePawn);

        // when & then
        verifyException("e4", "e3", RecursivePiece.INVALID_TARGET_POSITION);
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
