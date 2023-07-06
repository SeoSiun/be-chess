package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;


class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판 초기화 시 기물 배치를 검사합니다.")
    void create() {
        board.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = "........";
        assertEquals(
                appendNewLine("RNBQKBNR  8") +
                        appendNewLine("PPPPPPPP  7") +
                        appendNewLine(blankRank + "  6")+
                        appendNewLine(blankRank + "  5") +
                        appendNewLine(blankRank + "  4") +
                        appendNewLine(blankRank + "  3") +
                        appendNewLine("pppppppp  2") +
                        appendNewLine("rnbqkbnr  1") +
                        appendNewLine("") +
                        appendNewLine("abcdefgh"),
                board.showBoard());
    }

    @Test
    @DisplayName("pieceCount는 기물 색, 종류에 해당하는 기물의 개수를 반환합니다.")
    void pieceCount() {
        board.initialize();
        assertEquals(8, board.pieceCount(Piece.Color.WHITE, Piece.Type.PAWN));
        assertEquals(8, board.pieceCount(Piece.Color.BLACK, Piece.Type.PAWN));
        assertEquals(2, board.pieceCount(Piece.Color.WHITE, Piece.Type.KNIGHT));
        assertEquals(2, board.pieceCount(Piece.Color.BLACK, Piece.Type.KNIGHT));
        assertEquals(2, board.pieceCount(Piece.Color.WHITE, Piece.Type.ROOK));
        assertEquals(2, board.pieceCount(Piece.Color.BLACK, Piece.Type.ROOK));
        assertEquals(1, board.pieceCount(Piece.Color.WHITE, Piece.Type.KING));
        assertEquals(1, board.pieceCount(Piece.Color.BLACK, Piece.Type.KING));
        assertEquals(1, board.pieceCount(Piece.Color.WHITE, Piece.Type.QUEEN));
        assertEquals(1, board.pieceCount(Piece.Color.BLACK, Piece.Type.QUEEN));
        assertEquals(2, board.pieceCount(Piece.Color.WHITE, Piece.Type.BISHOP));
        assertEquals(2, board.pieceCount(Piece.Color.BLACK, Piece.Type.BISHOP));
    }

    @Test
    @DisplayName("findPiece는 주어진 좌표에 놓인 기물을 반환해야한다.")
    void findPiece() throws Exception {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    @DisplayName("move 결과 해당 위치에 해당 기물이 놓여야한다.")
    void move() throws Exception {
        board.initializeEmpty();

        String blankRank = "........";
        String position = "b5";

        Piece piece = Piece.createBlackRook();
        board.move(position, piece);


        assertEquals(piece, board.findPiece(position));
        assertEquals(
                appendNewLine(blankRank + "  8") +
                appendNewLine(blankRank + "  7") +
                appendNewLine(blankRank + "  6")+
                appendNewLine(".R......  5") +
                appendNewLine(blankRank + "  4") +
                appendNewLine(blankRank + "  3") +
                appendNewLine(blankRank + "  2") +
                appendNewLine(blankRank + "  1") +
                appendNewLine("") +
                appendNewLine("abcdefgh"),board.showBoard());

    }
}
