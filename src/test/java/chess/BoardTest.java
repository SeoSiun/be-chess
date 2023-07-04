package chess;

import chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    Board board;
    Pawn white;
    Pawn black;

    @BeforeEach
    public void init() {
        board = new Board();
        white = new Pawn(Pawn.WHITE_COLOR);
        black = new Pawn(Pawn.BLACK_COLOR);
    }

    @Test
    @DisplayName("Pawn 추가 테스트")
    public void create() {
        verifyAddPawnToBoard(white, 0);
        verifyAddPawnToBoard(black, 1);
    }

    private void verifyAddPawnToBoard(Pawn pawn, final int index) {
        board.add(pawn);
        assertEquals(index + 1, board.size());
        assertEquals(pawn, board.findPawn(index));
    }

    @Test
    @DisplayName("Board initialize시 흰색 폰 8개, 검은색 폰 8개가 놓여야 한다.")
    public void initialize(){
        Board board = new Board();
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }


    @Test
    @DisplayName("Board initialize시 흰색 폰이 1번째 줄, 검은색폰이 7번째 줄에 놓여야 한다.")
    public void run_chess() {
        Board board = new Board();
        board.initialize();
        assertEquals("........\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "pppppppp\n" +
                "........", board.print());
    }
}
