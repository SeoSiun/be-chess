package chess;

import chess.pieces.Piece;

import static utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Rank> ranks;

    public static final int MAX_RANK = 8;
    public static final int MAX_FILE = 8;

    public Board() {
        this.ranks = new ArrayList<>();
    }

    public void initialize() {
        ranks.add(Rank.createFirstBlackRank());
        ranks.add(Rank.createRankWithOnlyBlackPawn());
        for (int file = 2; file < MAX_FILE - 2; file++) {
            ranks.add(Rank.createBlankRank());
        }
        ranks.add(Rank.createRankWithOnlyWhitePawn());
        ranks.add(Rank.createFirstWhiteRank());
    }

    public int pieceCount() {
        int count = 0;

        for (Rank rank : ranks) {
            count += rank.pieceCount();
        }
        return count;
    }

    public int pieceCount(Piece.Color color, Piece.Type type) {
        int count = 0;

        for (Rank rank : ranks) {
            count += rank.pieceCount(color, type);
        }
        return count;
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        for (Rank rank : ranks) {
            sb.append(appendNewLine(rank.getRankRepresentation()));
        }
        return sb.toString();
    }
}
