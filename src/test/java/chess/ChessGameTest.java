package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.pieces.PieceFactory;
import chess.pieces.RecursivePiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessGameTest {
    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setup() {
        board = new Board();
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("source 위치에 있는 Piece가 target 위치로 옮겨져야 한다.")
    void moveFromSourceToTarget() {
        // given
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";

        // when
        chessGame.move(board, sourcePosition, targetPosition, Color.WHITE);

        // then
        assertEquals(PieceFactory.createBlank(), board.findPiece(sourcePosition));
        assertEquals(PieceFactory.createPieceByColorAndType(Color.WHITE, Type.PAWN), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("같은 색상의 기물이 있는 위치로 움직이는 경우 TargetSameColorException을 throw한다.")
    void moveWithSameColor() {
        // given
        board.initialize();
        String sourcePosition = "a1";
        String targetPosition = "a2";

        // when & then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> chessGame.move(board, sourcePosition, targetPosition, Color.WHITE));
        assertEquals(ChessGame.TARGET_IS_SAME_COLOR, exception.getMessage());
    }

    @Test
    @DisplayName("해당 기물이 움직일 수 없는 위치로 움직이는 경우 InvalidTargetPositionException을 throw한다.")
    void moveInvalidDirection() {
        // given
        board.initialize();
        String sourcePosition = "a2";
        String targetPosition = "b4";

        // when & then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> chessGame.move(board, sourcePosition, targetPosition, Color.WHITE));
        assertEquals(RecursivePiece.INVALID_TARGET_POSITION, exception.getMessage());
    }

    @Test
    @DisplayName("source와 target이 같은 경우 TargetSameAsSourceException을 throw한다.")
    void moveSamePosition() {
        // given
        board.initialize();
        String sourcePosition = "a2";
        String targetPosition = "a2";

        // when & then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> chessGame.move(board, sourcePosition, targetPosition, Color.WHITE));
        assertEquals(ChessGame.TARGET_EQUALS_SOURCE, exception.getMessage());
    }

    @Test
    @DisplayName("해당 색상 기물의 차례가 아닌 경우 예외가 발생한다.")
    void moveNotMyTurn() {
        // given
        board.initialize();
        String sourcePosition = "a2";
        String targetPosition = "a3";
        Color turn = Color.BLACK;

        // when & then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> chessGame.move(board, sourcePosition, targetPosition, turn));
        assertEquals(ChessView.appendTurnMessage(turn, ChessGame.INVALID_TURN), exception.getMessage());
    }
    
    @Test
    @DisplayName("해당 색상의 모든 기물의 점수 합계를 리턴해야한다.")
    void calculatePoint() {
        // given
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.PAWN));
        addPiece("e6", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.QUEEN));
        addPiece("b8", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.KING));
        addPiece("c8", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.ROOK));

        addPiece("f2", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.PAWN));
        addPiece("g2", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.PAWN));
        addPiece("e1", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.ROOK));
        addPiece("f1", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.KING));

        // when
        // then
        verifyCalculatedPoint(15.0, 7.0);
    }

    @Test
    @DisplayName("같은 file에 여러 개의 pawn이 있으면 pawn의 점수를 0.5점으로 계산해야 한다")
    void calculatePointWithMultiplePawnInSameLine() {
        // given
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.PAWN));
        addPiece("c6", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.PAWN));
        addPiece("c5", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.PAWN));
        addPiece("e6", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.QUEEN));
        addPiece("b8", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.KING));
        addPiece("c8", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.ROOK));
        addPiece("a7", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.PAWN));
        addPiece("c7", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.PAWN));
        addPiece("d7", PieceFactory.createPieceByColorAndType(Color.BLACK, Type.BISHOP));

        addPiece("f2", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.PAWN));
        addPiece("g2", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.PAWN));
        addPiece("e1", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.ROOK));
        addPiece("f1", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.KING));
        addPiece("f4", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.KNIGHT));
        addPiece("g4", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.QUEEN));
        addPiece("f3", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.PAWN));
        addPiece("h3", PieceFactory.createPieceByColorAndType(Color.WHITE, Type.PAWN));

        // when
        // then
        verifyCalculatedPoint(20.5, 19.5);
    }

    private void verifyCalculatedPoint(double blackExpected, double whiteExpected) {
        // when
        double blackPoint = chessGame.calculatePoint(board, Color.BLACK);
        double whitePoint = chessGame.calculatePoint(board, Color.WHITE);

        // then
        assertEquals(blackExpected, blackPoint, 0.01);
        assertEquals(whiteExpected, whitePoint, 0.01);
    }

    private void addPiece(String position, Piece piece) {
        board.move(Position.from(position), piece);
    }
}
