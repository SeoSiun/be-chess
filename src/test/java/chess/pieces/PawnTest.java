package chess.pieces;

import chess.Board;
import chess.ChessGame;
import chess.Position;
import exceptions.InvalidTargetPositionException;
import exceptions.PawnMoveDiagonalWithNoEnemyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PawnTest {
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
        Piece whitePawnForOneStep = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.PAWN);
        Piece whitePawnForTwoStep = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.PAWN);
        board.initializeEmpty();
        board.move(Position.from("b2"), whitePawnForOneStep);
        board.move(Position.from("c2"), whitePawnForTwoStep);

        // when & then
        verifyMovement(whitePawnForOneStep, "b2", "b3");
        verifyMovement(whitePawnForTwoStep, "c2", "c4");
    }

    @Test
    @DisplayName("Pawn은 두 번째 이동부터는 앞으로 한 칸만 움직일 수 있다.")
    void movePawnNonFirst() {
        // given
        Piece whitePawn = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.PAWN);
        board.initializeEmpty();
        board.move(Position.from("b2"), whitePawn);
        chessGame.move(board, "b2", "b4");

        // when & then
        verifyMovement(whitePawn, "b4", "b5");
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move(board, "b5", "b7"));
    }

    @Test
    @DisplayName("Pawn은 상대 편 기물이 있을 때만 대각선으로 이동할 수 있다.")
    void movePawnDiagonal() {
        // given
        Piece whitePawn = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.PAWN);
        Piece blackPawn = PieceFactory.createPiece(Piece.Color.BLACK, Piece.Type.PAWN);

        board.initializeEmpty();
        board.move(Position.from("b2"), whitePawn);
        board.move(Position.from("c3"), blackPawn);

        // when & then
        verifyMovement(whitePawn, "b2", "c3");
        assertThrows(PawnMoveDiagonalWithNoEnemyException.class, () -> chessGame.move(board, "c3", "d4"));
    }

    private void verifyMovement(Piece pieceToMove, String sourcePosition, String targetPosition) {
        // when
        chessGame.move(board, sourcePosition, targetPosition);

        // then
        assertEquals(pieceToMove, board.findPiece(targetPosition));
        assertEquals(blank, board.findPiece(sourcePosition));
    }
}
