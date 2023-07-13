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

class KnightTest {
    private Board board;
    private ChessGame chessGame;
    private final Piece blank = PieceFactory.createBlank();

    @BeforeEach
    void setup() {
        board = new Board();
        chessGame = new ChessGame();
    }

    /**
     * check Knight's Movement
     */
    @Test
    @DisplayName("Knight는 NNE, NNW, .. WWS 으로 움직일 수 있다.")
    void moveKnight() {
        // given
        Piece whiteKnight = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteKnight);
        String[] positions = {"d4", "e6", "d8", "e6", "d4", "f5", "h4", "f5", "d4"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteKnight, positions[index], positions[index + 1]));
    }

    @Test
    @DisplayName("Knight가 NNE, NNW, .. WWS 으로 여러 칸 움직이면 InvalidTargetPositionException이 발생한다.")
    void moveKnightMultipleStep() {
        // given
        Piece whiteKnight = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteKnight);

        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move(board, "d4", "f8"));
    }

    private void verifyMovement(Piece pieceToMove, String sourcePosition, String targetPosition) {
        // when
        chessGame.move(board, sourcePosition, targetPosition);

        // then
        assertEquals(pieceToMove, board.findPiece(targetPosition));
        assertEquals(blank, board.findPiece(sourcePosition));
    }
}
