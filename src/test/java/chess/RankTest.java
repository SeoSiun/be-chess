package chess;


import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankTest {
    @Test
    @DisplayName("InitialType에 따라 올바른 Piece를 포함하는 Rank가 생성되어야 한다.")
    public void createRank() {
        verifyRank(Rank.createFirstBlackRank(), 1);
        verifyRank(Rank.createFirstWhiteRank(), 2);
        verifyRank(Rank.createRankWithOnlyBlackPawn(), 3);
        verifyRank(Rank.createRankWithOnlyWhitePawn(), 4);
        verifyRank(Rank.createBlankRank(), 5);
    }

    private void verifyRank(Rank rank, int type) {
        int expectedCount = 8;
        String expectedRepresentation = "";

        switch (type) {
            case 1:
                expectedRepresentation = "RNBQKBNR";
                break;
            case 2:
                expectedRepresentation = "rnbqkbnr";
                break;
            case 3:
                expectedRepresentation = "PPPPPPPP";
                break;
            case 4:
                expectedRepresentation = "pppppppp";
                break;
            case 5:
                expectedCount = 0;
                expectedRepresentation = "........";
                break;
        }

        assertEquals(expectedCount, rank.pieceCount());
        assertEquals(expectedRepresentation, rank.getRankRepresentation());
    }
}
