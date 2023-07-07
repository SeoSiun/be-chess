package chess;


import chess.pieces.Piece;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankTest {
    @Test
    @DisplayName("InitialType에 따라 올바른 Piece를 포함하는 Rank가 생성되어야 한다.")
    public void create() {
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

    @Test
    @DisplayName("getPiecesByColor는 해당 rank에서 해당하는 색의 기물 목록을 리턴한다.")
    public void getPiecesByColor() {
        Rank rank = Rank.createFirstBlackRank();
        rank.setPiece(1, Piece.createWhiteQueen());
        rank.setPiece(5, Piece.createWhiteKnight());

        List<Piece> whitePieces = rank.getPiecesByColor(Piece.Color.WHITE);
        List<Piece> blackPieces = rank.getPiecesByColor(Piece.Color.BLACK);

        assertEquals(2, whitePieces.size());
        assertEquals(6, blackPieces.size());
        assertEquals("qn", getRepresentations(whitePieces));
        assertEquals("RBQKNR", getRepresentations(blackPieces));
    }

    public static String getRepresentations(List<Piece> pieces) {
        StringBuilder sb = new StringBuilder();

        for (Piece piece : pieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }
}
