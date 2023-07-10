package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;


class BoardTest {
    private Board board;
    private static final String BLANK_RANK = "........";


    @BeforeEach
    void setup() {
        board = new Board();
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
                board.showBoard());
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
                board.showBoard());
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
                board.showBoard());
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
        int whitePawnCount = board.pieceCount(Piece.Color.WHITE, Piece.Type.PAWN);
        int blackPawnCount = board.pieceCount(Piece.Color.BLACK, Piece.Type.PAWN);
        int whiteKnightCount = board.pieceCount(Piece.Color.WHITE, Piece.Type.KNIGHT);
        int blackKnightCount = board.pieceCount(Piece.Color.BLACK, Piece.Type.KNIGHT);
        int whiteRookCount = board.pieceCount(Piece.Color.WHITE, Piece.Type.ROOK);
        int blackRookCount = board.pieceCount(Piece.Color.BLACK, Piece.Type.ROOK);
        int whiteKingCount = board.pieceCount(Piece.Color.WHITE, Piece.Type.KING);
        int blackKingCount = board.pieceCount(Piece.Color.BLACK, Piece.Type.KING);
        int whiteQueenCount = board.pieceCount(Piece.Color.WHITE, Piece.Type.QUEEN);
        int blackQueenCount = board.pieceCount(Piece.Color.BLACK, Piece.Type.QUEEN);
        int whiteBishopCount = board.pieceCount(Piece.Color.WHITE, Piece.Type.BISHOP);
        int blackBishopCount = board.pieceCount(Piece.Color.BLACK, Piece.Type.BISHOP);

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
        assertEquals(Piece.createBlackRook(), pieceIna8);
        assertEquals(Piece.createBlackRook(), pieceInh8);
        assertEquals(Piece.createWhiteRook(), pieceIna1);
        assertEquals(Piece.createWhiteRook(), pieceInh1);
    }

    @Test
    @DisplayName("지정한 위치에 기물이 놓여야한다.")
    void move() {
        // given
        board.initializeEmpty();
        String position = "b5";
        Piece piece = Piece.createBlackRook();

        // when
        board.move(position, piece);

        // then
        assertEquals(piece, board.findPiece(position));
        assertEquals(
                appendNewLine(BLANK_RANK + "  8") +
                        appendNewLine(BLANK_RANK + "  7") +
                        appendNewLine(BLANK_RANK + "  6") +
                        appendNewLine(".R......  5") +
                        appendNewLine(BLANK_RANK + "  4") +
                        appendNewLine(BLANK_RANK + "  3") +
                        appendNewLine(BLANK_RANK + "  2") +
                        appendNewLine(BLANK_RANK + "  1") +
                        appendNewLine("") +
                        appendNewLine("abcdefgh"), board.showBoard());

    }

    @Test
    @DisplayName("해당 색상의 모든 기물의 점수 합계를 리턴해야한다.")
    void calculatePoint() throws Exception {
        // given
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        // when
        // then
        verifyCalculatedPoint(15.0, 7.0);
    }

    @Test
    @DisplayName("같은 file에 여러 개의 pawn이 있으면 pawn의 점수를 0.5점으로 계산해야 한다")
    void calculatePointWithMultiplePawnInSameLine() throws Exception {
        // given
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("c6", Piece.createBlackPawn());
        addPiece("c5", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());
        addPiece("a7", Piece.createBlackPawn());
        addPiece("c7", Piece.createBlackPawn());
        addPiece("d7", Piece.createBlackBishop());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());
        addPiece("f4", Piece.createWhiteKnight());
        addPiece("g4", Piece.createWhiteQueen());
        addPiece("f3", Piece.createWhitePawn());
        addPiece("h3", Piece.createWhitePawn());

        // when
        // then
        verifyCalculatedPoint(20.5, 19.5);
    }

    private void verifyCalculatedPoint(double blackExpected, double whiteExpected) {
        // when
        double blackPoint = board.calculatePoint(Piece.Color.BLACK);
        double whitePoint = board.calculatePoint(Piece.Color.WHITE);

        // then
        assertEquals(blackExpected, blackPoint, 0.01);
        assertEquals(whiteExpected, whitePoint, 0.01);
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }

    @Test
    @DisplayName("해당 색상의 기물들이 점수 순서로 정렬되어서 반환되어야한다.")
    void getSortedPiecesByPoint() {
        // given
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("e5", Piece.createBlackBishop());
        addPiece("e3", Piece.createBlackKnight());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        // when
        List<Piece> sortedWhitePieces = board.getSortedPiecesByPoint(Piece.Color.WHITE);
        List<Piece> sortedBlackPieces = board.getSortedPiecesByPoint(Piece.Color.BLACK);

        // then
        assertEquals("rppk", RankTest.getRepresentations(sortedWhitePieces));
        assertEquals("QRBNPK", RankTest.getRepresentations(sortedBlackPieces));
    }

    @Test
    @DisplayName("source 위치에 있는 Piece가 target 위치로 옮겨져야 한다.")
    void moveFromSourceToTarget() throws Exception {
        // given
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";

        // when
        board.move(sourcePosition, targetPosition);

        // then
        assertEquals(Piece.createBlank(), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(), board.findPiece(targetPosition));
    }
}
