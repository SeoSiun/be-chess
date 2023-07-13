package chess;


import chess.pieces.Piece;
import chess.pieces.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RankTest {
    Rank firstBlackRank = RankFactory.createFirstRank(Piece.Color.BLACK);
    Rank firstWhiteRank = RankFactory.createFirstRank(Piece.Color.WHITE);

    @Test
    @DisplayName("각 팩토리메소드마다 올바른 Piece를 포함하는 Rank가 생성되어야 한다.")
    void create() {
        // given

        // when
        Rank firstBlackRank = RankFactory.createFirstRank(Piece.Color.BLACK);
        Rank firstWhiteRank = RankFactory.createFirstRank(Piece.Color.WHITE);
        Rank rankWithOnlyBlackPawn = RankFactory.createRankWithOneColorAndType(Piece.Color.BLACK, Piece.Type.PAWN);
        Rank rankWithOnlyWhitePawn = RankFactory.createRankWithOneColorAndType(Piece.Color.WHITE, Piece.Type.PAWN);
        Rank blankRank = RankFactory.createBlankRank();

        // then
        verifyRank(firstBlackRank, 1);
        verifyRank(firstWhiteRank, 2);
        verifyRank(rankWithOnlyBlackPawn, 3);
        verifyRank(rankWithOnlyWhitePawn, 4);
        verifyRank(blankRank, 5);
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
        assertEquals(expectedRepresentation, rank.getRepresentation());
    }

    @Test
    @DisplayName("index에 해당하는 piece를 반환해야 한다.")
    void getPiece() {
        // given
        Rank rank = RankFactory.createFirstRank(Piece.Color.BLACK);
        int index = 1;

        // when
        Piece pieceInIndex1 = rank.getPiece(index);

        // Then
        assertTrue(pieceInIndex1.checkColorAndType(Piece.Color.BLACK, Piece.Type.KNIGHT));
        assertEquals('N', pieceInIndex1.getRepresentation());
    }

    @Test
    @DisplayName("모든 기물의 개수를 반환해야 한다.")
    void pieceCount() {
        // given
        Rank rankWith8Pieces = RankFactory.createFirstRank(Piece.Color.BLACK);
        Rank blankRank = RankFactory.createBlankRank();

        // when
        int rankWith8PiecesCount = rankWith8Pieces.pieceCount();
        int blankRankCount = blankRank.pieceCount();

        // then
        assertEquals(8, rankWith8PiecesCount);
        assertEquals(0, blankRankCount);
    }

    @Test
    @DisplayName("index에 해당하는 위치에 해당 Piece가 놓여야 한다.")
    void setPiece() {
        // given
        Rank rank = RankFactory.createBlankRank();
        int index = 1;
        Piece whitePawn = PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.PAWN);

        // when
        rank.setPiece(index, whitePawn);

        // then
        assertEquals(1, rank.pieceCount());
        assertEquals(whitePawn, rank.getPiece(index));
    }

    @Test
    @DisplayName("color, type에 해당하는 모든 기물의 개수를 반환해야 한다.")
    void pieceCountWithColorAndType() {
        // given
        Rank rank = RankFactory.createFirstRank(Piece.Color.BLACK);
        rank.setPiece(1, PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.KNIGHT));
        rank.setPiece(4, PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.KNIGHT));

        // when
        int pieceCountWithWhiteKnight = rank.pieceCount(Piece.Color.WHITE, Piece.Type.KNIGHT);
        int pieceCountWithBlackBishop = rank.pieceCount(Piece.Color.BLACK, Piece.Type.BISHOP);
        int pieceCountWithBlackQueen = rank.pieceCount(Piece.Color.BLACK, Piece.Type.QUEEN);

        // then
        assertEquals(2, pieceCountWithWhiteKnight);
        assertEquals(2, pieceCountWithBlackBishop);
        assertEquals(1, pieceCountWithBlackQueen);
    }

    @Test
    @DisplayName("해당 rank의 representation이 반환되어야 한다.")
    void getRepresentation() {
        // given

        // when
        String firstBlackRankRepresentation = firstBlackRank.getRepresentation();
        String firstWhiteRankRepresentation = firstWhiteRank.getRepresentation();

        // then
        assertEquals("RNBQKBNR", firstBlackRankRepresentation);
        assertEquals("rnbqkbnr", firstWhiteRankRepresentation);
    }

    @Test
    @DisplayName("rank에서 해당하는 색의 기물 목록이 리턴되어야 한다.")
    void getPiecesByColor() {
        // given
        Rank rank = RankFactory.createFirstRank(Piece.Color.BLACK);
        rank.setPiece(1, PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.QUEEN));
        rank.setPiece(5, PieceFactory.createPieceByColorAndType(Piece.Color.WHITE, Piece.Type.KNIGHT));

        // when
        List<Piece> whitePieces = rank.getPiecesByColor(Piece.Color.WHITE);
        List<Piece> blackPieces = rank.getPiecesByColor(Piece.Color.BLACK);

        //then
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
