package chess;

import chess.pieces.*;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;

public class RankFactory {
    private RankFactory() {}
    public static Rank createFirstRank(Color color) {
        Rank rank = new Rank();

        rank.addPiece(PieceFactory.createPieceByColorAndType(color, Type.ROOK));
        rank.addPiece(PieceFactory.createPieceByColorAndType(color, Type.KNIGHT));
        rank.addPiece(PieceFactory.createPieceByColorAndType(color, Type.BISHOP));
        rank.addPiece(PieceFactory.createPieceByColorAndType(color, Type.QUEEN));
        rank.addPiece(PieceFactory.createPieceByColorAndType(color, Type.KING));
        rank.addPiece(PieceFactory.createPieceByColorAndType(color, Type.BISHOP));
        rank.addPiece(PieceFactory.createPieceByColorAndType(color, Type.KNIGHT));
        rank.addPiece(PieceFactory.createPieceByColorAndType(color, Type.ROOK));

        return rank;
    }

    public static Rank createBlankRank() {
        return createRankWithOneColorAndType(Color.NO_COLOR, Type.NO_PIECE);
    }

    public static Rank createRankWithOneColorAndType(Color color, Type type) {
        Rank rank = new Rank();

        for (int file = 0; file < Board.MAX_FILE; file++) {
            rank.addPiece(PieceFactory.createPieceByColorAndType(color, type));
        }
        return rank;

    }
}
