package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.BoardTest.BLANK_RANK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

class ChessViewTest {
    private Board board;
    private ChessView chessView;

    @BeforeEach
    void setup() {
        board = new Board();
        chessView = new ChessView(board);
    }

    @Test
    @DisplayName("기물 위치가 올바르게 출력되어야 한다.")
    void showBoard() {
        // given
        board.initialize();

        // when
        String showBoard = chessView.showBoard();

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
                showBoard);
    }
}
