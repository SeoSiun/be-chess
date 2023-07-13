package chess.pieces;

import chess.Board;
import chess.ChessGame;
import chess.Position;
import exceptions.PositionOutOfRangeException;
import exceptions.UnreachableWithObstacleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueenTest {
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
                .forEach(index -> verifyMovement(whiteQueen, positions[index], positions[index + 1]));

    }

    @Test
    @DisplayName("Queen이 체스판을 벗어나는 위치로 이동하면 PositionOutOfRangeException이 발생해야 한다.")
    void moveQueenOutOfRange() {
        // given
        Piece whiteKing = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteKing);

        // when & then
        assertThrows(PositionOutOfRangeException.class, () -> chessGame.move(board, "e5", "e9"));
    }

    @Test
    @DisplayName("Queen이 이동하는 경로에 다른 기물이 있으면 UnreachableWithObstacleException이 발생해야 한다.")
    void moveQueenWithObstacle() {
        // given
        Piece whiteKing = PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN);
        board.initializeEmpty();
        board.move(Position.from("d1"), whiteKing);
        board.move(Position.from("d3"), PieceFactory.createPiece(Piece.Color.WHITE, Piece.Type.PAWN));

        // when & then
        assertThrows(UnreachableWithObstacleException.class, () -> chessGame.move(board, "d1", "d5"));
    }

    private void verifyMovement(Piece pieceToMove, String sourcePosition, String targetPosition) {
        // when
        chessGame.move(board, sourcePosition, targetPosition);

        // then
        assertEquals(pieceToMove, board.findPiece(targetPosition));
        assertEquals(blank, board.findPiece(sourcePosition));
    }
}
