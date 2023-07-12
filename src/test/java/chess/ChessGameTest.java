package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.pieces.PieceFactory;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChessGameTest {
    private Board board;
    private ChessView chessView;
    private ChessGame chessGame;

    private final Piece blank = PieceFactory.createBlank();


    @BeforeEach
    void setup() {
        board = new Board();
        chessView = new ChessView(board);
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("source 위치에 있는 Piece가 target 위치로 옮겨져야 한다.")
    void moveFromSourceToTarget() throws Exception {
        // given
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";

        // when
        chessGame.move(sourcePosition, targetPosition, true);

        // then
        assertEquals(PieceFactory.createBlank(), board.findPiece(sourcePosition));
        assertEquals(PieceFactory.createPiece(Color.WHITE, Type.PAWN), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("sourcePosition에 기물이 존재하지 않는 경우 NoPieceInSourceException을 throw한다.")
    void moveNoPiece() {
        // given
        board.initialize();
        String sourcePosition = "a5";
        String targetPosition = "a2";

        // when & then
        assertThrows(NoPieceInSourceException.class, () -> chessGame.move(sourcePosition, targetPosition, true));
    }

    @Test
    @DisplayName("같은 색상의 기물이 있는 위치로 움직이는 경우 TargetSameColorException을 throw한다.")
    void moveWithSameColor() {
        // given
        board.initialize();
        String sourcePosition = "a1";
        String targetPosition = "a2";

        // when & then
        assertThrows(TargetSameColorException.class, () -> chessGame.move(sourcePosition, targetPosition, true));
    }

    @Test
    @DisplayName("해당 기물이 움직일 수 없는 위치로 움직이는 경우 InvalidTargetPositionException을 throw한다.")
    void moveInvalidDirection() {
        // given
        board.initialize();
        String sourcePosition = "a2";
        String targetPosition = "b4";

        // when & then
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move(sourcePosition, targetPosition, true));
    }

    @Test
    @DisplayName("source와 target이 같은 경우 TargetSameAsSourceException을 throw한다.")
    void moveSamePosition() {
        // given
        board.initialize();
        String sourcePosition = "a2";
        String targetPosition = "a2";

        // when & then
        assertThrows(TargetSameAsSourceException.class, () -> chessGame.move(sourcePosition, targetPosition, true));
    }

    /**
     * check King's movement
     */
    @Test
    @DisplayName("target까지 가는 길에 장애물(다른 기물)이 있다면 UnreachableWithObstacleException을 throw한다.")
    void moveUnreachableTarget() {
        // given
        board.initialize();
        String sourcePosition = "a1";
        String targetPosition = "a3";

        // when & then
        assertThrows(UnreachableWithObstacleException.class, () -> chessGame.move(sourcePosition, targetPosition, true));
    }

    @Test
    @DisplayName("King은 어느방향이든 1칸 움직일 수 있다.")
    void moveKing() {
        // given
        Piece whiteKing = PieceFactory.createPiece(Color.WHITE, Type.KING);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteKing);
        String[] positions = {"e5", "e6", "f7", "g7", "h6", "h5", "g4", "f4", "e5"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteKing, positions[index], positions[index + 1], true));
    }

    @Test
    @DisplayName("King이 체스판을 벗어나는 위치로 이동하면 PositionOutOfRangeException이 발생해야 한다.")
    void moveKingOutOfRange() {
        // given
        Piece whiteKing = PieceFactory.createPiece(Color.WHITE, Type.KING);
        board.initializeEmpty();
        board.move(Position.from("e8"), whiteKing);

        // when & then
        assertThrows(PositionOutOfRangeException.class, () -> chessGame.move("e8", "e9", true));
    }

    @Test
    @DisplayName("King이 1칸 이상 움직이면 InvalidTargetPositionException이 발생해야 한다.")
    void moveKingMultipleStep() {
        // given
        Piece whiteKing = PieceFactory.createPiece(Color.WHITE, Type.KING);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteKing);

        // when & then
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move("e5", "e2", true));
    }

    @Test
    @DisplayName("King이 이동하려는 위치에 같은 색의 기물이 있으면 TargetSameColorException이 발생해야 한다.")
    void moveKingWithObstacle() {
        // given
        Piece whiteKing = PieceFactory.createPiece(Color.WHITE, Type.KING);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteKing);
        board.move(Position.from("e4"), PieceFactory.createPiece(Color.WHITE, Type.PAWN));

        // when & then
        assertThrows(TargetSameColorException.class, () -> chessGame.move("e5", "e4", true));
    }


    /**
     * check Queen's Movement
     */
    @Test
    @DisplayName("Queen은 대각선, 수직, 수평 방향으로 몇 칸이든 이동할 수 있다.")
    void moveQueen() {
        // given
        Piece whiteQueen = PieceFactory.createPiece(Color.WHITE, Type.QUEEN);
        board.initializeEmpty();
        board.move(Position.from("d1"), whiteQueen);
        String[] positions = {"d1", "d5", "g8", "c8", "a6", "a5", "e1", "c1", "a3"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteQueen, positions[index], positions[index + 1], true));

    }

    @Test
    @DisplayName("Queen이 체스판을 벗어나는 위치로 이동하면 PositionOutOfRangeException이 발생해야 한다.")
    void moveQueenOutOfRange() {
        // given
        Piece whiteKing = PieceFactory.createPiece(Color.WHITE, Type.QUEEN);
        board.initializeEmpty();
        board.move(Position.from("e5"), whiteKing);

        // when & then
        assertThrows(PositionOutOfRangeException.class, () -> chessGame.move("e5", "e9", true));
    }

    @Test
    @DisplayName("Queen이 이동하는 경로에 다른 기물이 있으면 UnreachableWithObstacleException이 발생해야 한다.")
    void moveQueenWithObstacle() {
        // given
        Piece whiteKing = PieceFactory.createPiece(Color.WHITE, Type.QUEEN);
        board.initializeEmpty();
        board.move(Position.from("d1"), whiteKing);
        board.move(Position.from("d3"), PieceFactory.createPiece(Color.WHITE, Type.PAWN));

        // when & then
        assertThrows(UnreachableWithObstacleException.class, () -> chessGame.move("d1", "d5", true));
    }


    /**
     * check Knight's Movement
     */
    @Test
    @DisplayName("Knight는 NNE, NNW, .. WWS 으로 움직일 수 있다.")
    void moveKnight() {
        // given
        Piece whiteKnight = PieceFactory.createPiece(Color.WHITE, Type.KNIGHT);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteKnight);
        String[] positions = {"d4", "e6", "d8", "e6", "d4", "f5", "h4", "f5", "d4"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteKnight, positions[index], positions[index + 1], true));
    }

    @Test
    @DisplayName("Knight가 NNE, NNW, .. WWS 으로 여러 칸 움직이면 InvalidTargetPositionException이 발생한다.")
    void moveKnightMultipleStep() {
        // given
        Piece whiteKnight = PieceFactory.createPiece(Color.WHITE, Type.KNIGHT);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteKnight);

        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move("d4", "f8", true));
    }


    /**
     * check Bishop's movement
     */
    @Test
    @DisplayName("Bishop은 대각선 방향이라면 몇 칸이든 움직일 수 있다")
    void moveBishop() {
        // given
        Piece whiteBishop = PieceFactory.createPiece(Color.WHITE, Type.BISHOP);
        board.initializeEmpty();
        board.move(Position.from("f1"), whiteBishop);
        String[] positions = {"f1", "g2", "d5", "b3", "d1"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteBishop, positions[index], positions[index + 1], true));
    }

    @Test
    @DisplayName("Bishop이 대각선이 아닌 방향으로 움직이면 InvalidTargetPositionException이 발생한다.")
    void moveBishopNonDiagonal() {
        // given
        Piece whiteBishop = PieceFactory.createPiece(Color.WHITE, Type.BISHOP);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteBishop);

        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move("d4", "d8", true));
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move("d4", "a4", true));
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move("d4", "g6", true));
    }

    /**
     * check Rook's movement
     */
    @Test
    @DisplayName("Rook은 수평/수직 방향이라면 몇 칸이든 움직일 수 있다")
    void moveRook() {
        // given
        Piece whiteRook = PieceFactory.createPiece(Color.WHITE, Type.ROOK);
        board.initializeEmpty();
        board.move(Position.from("a1"), whiteRook);
        String[] positions = {"a1", "a4", "e4", "e3", "c3"};

        // when & then
        IntStream.range(0, positions.length - 1)
                .forEach(index -> verifyMovement(whiteRook, positions[index], positions[index + 1], true));
    }

    @Test
    @DisplayName("Rook이 수직/수평이 아닌 방향으로 움직이면 InvalidTargetPositionException이 발생한다.")
    void moveRookInvalidDirection() {
        // given
        Piece whiteRook = PieceFactory.createPiece(Color.WHITE, Type.ROOK);
        board.initializeEmpty();
        board.move(Position.from("d4"), whiteRook);

        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move("d4", "a7", true));
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move("d4", "g1", true));
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move("d4", "g6", true));
    }

    /**
     * check pawn's movement
     */
    @Test
    @DisplayName("Pawn은 첫 번째 이동에는 앞으로 두 칸 또는 한 칸 움직일 수 있다.")
    void movePawnFirst() {
        // given
        Piece whitePawnForOneStep = PieceFactory.createPiece(Color.WHITE, Type.PAWN);
        Piece whitePawnForTwoStep = PieceFactory.createPiece(Color.WHITE, Type.PAWN);
        board.initializeEmpty();
        board.move(Position.from("b2"), whitePawnForOneStep);
        board.move(Position.from("c2"), whitePawnForTwoStep);

        // when & then
        verifyMovement(whitePawnForOneStep, "b2", "b3", true);
        verifyMovement(whitePawnForTwoStep, "c2", "c4", true);
    }

    @Test
    @DisplayName("Pawn은 두 번째 이동부터는 앞으로 한 칸만 움직일 수 있다.")
    void movePawnNonFirst() {
        // given
        Piece whitePawn = PieceFactory.createPiece(Color.WHITE, Type.PAWN);
        board.initializeEmpty();
        board.move(Position.from("b2"), whitePawn);
        chessGame.move("b2", "b4", true);

        // when & then
        verifyMovement(whitePawn, "b4", "b5", true);
        assertThrows(InvalidTargetPositionException.class, () -> chessGame.move("b5", "b7", true));
    }

    @Test
    @DisplayName("Pawn은 상대 편 기물이 있을 때만 대각선으로 이동할 수 있다.")
    void movePawnDiagonal() {
        // given
        Piece whitePawn = PieceFactory.createPiece(Color.WHITE, Type.PAWN);
        Piece blackPawn = PieceFactory.createPiece(Color.BLACK, Type.PAWN);

        board.initializeEmpty();
        board.move(Position.from("b2"), whitePawn);
        board.move(Position.from("c3"), blackPawn);

        // when & then
        verifyMovement(whitePawn, "b2", "c3", true);
        assertThrows(PawnMoveDiagonalWithNoEnemyException.class, () -> chessGame.move("c3", "d4", true));
    }

    @Test
    @DisplayName("흰색 턴일 때 검은색 기물을 움직이면 InvalidTurnException이 throw된다.")
    void invalidTurn() {
        // given
        Piece blackPawn = PieceFactory.createPiece(Color.BLACK, Type.PAWN);

        board.initializeEmpty();
        board.move(Position.from("b7"), blackPawn);

        // when & then
        assertThrows(InvalidTurnException.class, () -> chessGame.move("b7", "b6", true));
    }

    private void verifyMovement(Piece pieceToMove, String sourcePosition, String targetPosition, boolean isWhiteTurn) {
        // when
        chessGame.move(sourcePosition, targetPosition, isWhiteTurn);

        // then
        assertEquals(pieceToMove, board.findPiece(targetPosition));
        assertEquals(blank, board.findPiece(sourcePosition));
    }
}
