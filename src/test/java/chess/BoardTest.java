package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.pieces.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;


class BoardTest {
    private Board board;
    private ChessView chessView;
    private ChessGame chessGame;
    public static final String BLANK_RANK = "........";


    @BeforeEach
    void setup() {
        board = new Board();
        chessView = new ChessView();
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("기물이 초기 위치에 올바르게 배치되어야 한다.")
    void initialize() {
        // given

        // when
        board.initialize();

        // then
        assertEquals(32, board.pieceCount());
        assertEquals(
                appendNewLine("RNBQKBNR  8") +
                        appendNewLine("PPPPPPPP  7") +
                        appendNewLine(BLANK_RANK + "  6") +
                        appendNewLine(BLANK_RANK + "  5") +
                        appendNewLine(BLANK_RANK + "  4") +
                        appendNewLine(BLANK_RANK + "  3") +
                        appendNewLine("pppppppp  2") +
                        appendNewLine("rnbqkbnr  1") +
                        appendNewLine("") +
                        appendNewLine("abcdefgh"),
                chessView.showBoard(board));
    }

    @Test
    @DisplayName("여러 번 initialize해도 같은 결과가 나와야 한다.")
    void multipleInitialize() {
        // given

        // when
        board.initialize();
        board.initialize();

        // then
        assertEquals(32, board.pieceCount());
        assertEquals(
                appendNewLine("RNBQKBNR  8") +
                        appendNewLine("PPPPPPPP  7") +
                        appendNewLine(BLANK_RANK + "  6") +
                        appendNewLine(BLANK_RANK + "  5") +
                        appendNewLine(BLANK_RANK + "  4") +
                        appendNewLine(BLANK_RANK + "  3") +
                        appendNewLine("pppppppp  2") +
                        appendNewLine("rnbqkbnr  1") +
                        appendNewLine("") +
                        appendNewLine("abcdefgh"),
                chessView.showBoard(board));
    }

    @Test
    @DisplayName("기물이 없는 체스판이 초기화되어야 한다.")
    void initializeEmpty() {
        // given

        // when
        board.initializeEmpty();

        // then
        assertEquals(0, board.pieceCount());
        assertEquals(
                appendNewLine(BLANK_RANK + "  8") +
                        appendNewLine(BLANK_RANK + "  7") +
                        appendNewLine(BLANK_RANK + "  6") +
                        appendNewLine(BLANK_RANK + "  5") +
                        appendNewLine(BLANK_RANK + "  4") +
                        appendNewLine(BLANK_RANK + "  3") +
                        appendNewLine(BLANK_RANK + "  2") +
                        appendNewLine(BLANK_RANK + "  1") +
                        appendNewLine("") +
                        appendNewLine("abcdefgh"),
                chessView.showBoard(board));
    }

    @Test
    @DisplayName("모든 기물의 개수를 반환해야 한다.")
    void pieceCount() {
        // given
        board.initialize();

        // when
        int pieceCount = board.pieceCount();

        // then
        assertEquals(32, pieceCount);
    }

    @Test
    @DisplayName("각각의 색, 종류에 해당하는 기물의 개수를 반환해야 한다.")
    void pieceCountWithColorAndType() {
        // given
        board.initialize();

        // when
        int whitePawnCount = board.pieceCount(Color.WHITE, Type.PAWN);
        int blackPawnCount = board.pieceCount(Color.BLACK, Type.PAWN);
        int whiteKnightCount = board.pieceCount(Color.WHITE, Type.KNIGHT);
        int blackKnightCount = board.pieceCount(Color.BLACK, Type.KNIGHT);
        int whiteRookCount = board.pieceCount(Color.WHITE, Type.ROOK);
        int blackRookCount = board.pieceCount(Color.BLACK, Type.ROOK);
        int whiteKingCount = board.pieceCount(Color.WHITE, Type.KING);
        int blackKingCount = board.pieceCount(Color.BLACK, Type.KING);
        int whiteQueenCount = board.pieceCount(Color.WHITE, Type.QUEEN);
        int blackQueenCount = board.pieceCount(Color.BLACK, Type.QUEEN);
        int whiteBishopCount = board.pieceCount(Color.WHITE, Type.BISHOP);
        int blackBishopCount = board.pieceCount(Color.BLACK, Type.BISHOP);

        // then
        assertEquals(8, whitePawnCount);
        assertEquals(8, blackPawnCount);
        assertEquals(2, whiteKnightCount);
        assertEquals(2, blackKnightCount);
        assertEquals(2, whiteRookCount);
        assertEquals(2, blackRookCount);
        assertEquals(1, whiteKingCount);
        assertEquals(1, blackKingCount);
        assertEquals(1, whiteQueenCount);
        assertEquals(1, blackQueenCount);
        assertEquals(2, whiteBishopCount);
        assertEquals(2, blackBishopCount);
    }

    @Test
    @DisplayName("주어진 좌표에 놓인 기물을 반환해야한다.")
    void findPiece() throws Exception {
        // given
        board.initialize();

        // when
        Piece pieceIna8 = board.findPiece("a8");
        Piece pieceInh8 = board.findPiece("h8");
        Piece pieceIna1 = board.findPiece("a1");
        Piece pieceInh1 = board.findPiece("h1");

        // then
        assertEquals(PieceFactory.createPiece(Color.BLACK, Type.ROOK), pieceIna8);
        assertEquals(PieceFactory.createPiece(Color.BLACK, Type.ROOK), pieceInh8);
        assertEquals(PieceFactory.createPiece(Color.WHITE, Type.ROOK), pieceIna1);
        assertEquals(PieceFactory.createPiece(Color.WHITE, Type.ROOK), pieceInh1);
    }

    @Test
    @DisplayName("해당 색상의 모든 기물의 점수 합계를 리턴해야한다.")
    void calculatePoint() throws Exception {
        // given
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createPiece(Color.BLACK, Type.PAWN));
        addPiece("e6", PieceFactory.createPiece(Color.BLACK, Type.QUEEN));
        addPiece("b8", PieceFactory.createPiece(Color.BLACK, Type.KING));
        addPiece("c8", PieceFactory.createPiece(Color.BLACK, Type.ROOK));

        addPiece("f2", PieceFactory.createPiece(Color.WHITE, Type.PAWN));
        addPiece("g2", PieceFactory.createPiece(Color.WHITE, Type.PAWN));
        addPiece("e1", PieceFactory.createPiece(Color.WHITE, Type.ROOK));
        addPiece("f1", PieceFactory.createPiece(Color.WHITE, Type.KING));

        // when
        // then
        verifyCalculatedPoint(15.0, 7.0);
    }

    @Test
    @DisplayName("같은 file에 여러 개의 pawn이 있으면 pawn의 점수를 0.5점으로 계산해야 한다")
    void calculatePointWithMultiplePawnInSameLine() throws Exception {
        // given
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createPiece(Color.BLACK, Type.PAWN));
        addPiece("c6", PieceFactory.createPiece(Color.BLACK, Type.PAWN));
        addPiece("c5", PieceFactory.createPiece(Color.BLACK, Type.PAWN));
        addPiece("e6", PieceFactory.createPiece(Color.BLACK, Type.QUEEN));
        addPiece("b8", PieceFactory.createPiece(Color.BLACK, Type.KING));
        addPiece("c8", PieceFactory.createPiece(Color.BLACK, Type.ROOK));
        addPiece("a7", PieceFactory.createPiece(Color.BLACK, Type.PAWN));
        addPiece("c7", PieceFactory.createPiece(Color.BLACK, Type.PAWN));
        addPiece("d7", PieceFactory.createPiece(Color.BLACK, Type.BISHOP));

        addPiece("f2", PieceFactory.createPiece(Color.WHITE, Type.PAWN));
        addPiece("g2", PieceFactory.createPiece(Color.WHITE, Type.PAWN));
        addPiece("e1", PieceFactory.createPiece(Color.WHITE, Type.ROOK));
        addPiece("f1", PieceFactory.createPiece(Color.WHITE, Type.KING));
        addPiece("f4", PieceFactory.createPiece(Color.WHITE, Type.KNIGHT));
        addPiece("g4", PieceFactory.createPiece(Color.WHITE, Type.QUEEN));
        addPiece("f3", PieceFactory.createPiece(Color.WHITE, Type.PAWN));
        addPiece("h3", PieceFactory.createPiece(Color.WHITE, Type.PAWN));

        // when
        // then
        verifyCalculatedPoint(20.5, 19.5);
    }

    private void verifyCalculatedPoint(double blackExpected, double whiteExpected) {
        // when
        double blackPoint = board.calculatePoint(Color.BLACK);
        double whitePoint = board.calculatePoint(Color.WHITE);

        // then
        assertEquals(blackExpected, blackPoint, 0.01);
        assertEquals(whiteExpected, whitePoint, 0.01);
    }

    private void addPiece(String position, Piece piece) {
        board.move(Position.from(position), piece);
    }

    @Test
    @DisplayName("해당 색상의 기물들이 점수 순서로 정렬되어서 반환되어야한다.")
    void getSortedPiecesByPoint() {
        // given
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createPiece(Color.BLACK, Type.PAWN));
        addPiece("e6", PieceFactory.createPiece(Color.BLACK, Type.QUEEN));
        addPiece("e5", PieceFactory.createPiece(Color.BLACK, Type.BISHOP));
        addPiece("e3", PieceFactory.createPiece(Color.BLACK, Type.KNIGHT));
        addPiece("b8", PieceFactory.createPiece(Color.BLACK, Type.KING));
        addPiece("c8", PieceFactory.createPiece(Color.BLACK, Type.ROOK));

        addPiece("f2", PieceFactory.createPiece(Color.WHITE, Type.PAWN));
        addPiece("g2", PieceFactory.createPiece(Color.WHITE, Type.PAWN));
        addPiece("e1", PieceFactory.createPiece(Color.WHITE, Type.ROOK));
        addPiece("f1", PieceFactory.createPiece(Color.WHITE, Type.KING));

        // when
        List<Piece> sortedWhitePieces = board.getSortedPiecesByPoint(Color.WHITE);
        List<Piece> sortedBlackPieces = board.getSortedPiecesByPoint(Color.BLACK);

        // then
        assertEquals("rppk", RankTest.getRepresentations(sortedWhitePieces));
        assertEquals("QRBNPK", RankTest.getRepresentations(sortedBlackPieces));
    }
}
