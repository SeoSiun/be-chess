package chess;

import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private List<Piece> pieces;

    private Rank() {
        pieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public Piece getPiece(int index) {
        return pieces.get(index);
    }

    public void setPiece(int index, Piece piece) {
        pieces.set(index, piece);
    }

    public static Rank createFirstWhiteRank() {
        Rank rank = new Rank();

        rank.addPiece(Piece.createWhiteRook());
        rank.addPiece(Piece.createWhiteKnight());
        rank.addPiece(Piece.createWhiteBishop());
        rank.addPiece(Piece.createWhiteQueen());
        rank.addPiece(Piece.createWhiteKing());
        rank.addPiece(Piece.createWhiteBishop());
        rank.addPiece(Piece.createWhiteKnight());
        rank.addPiece(Piece.createWhiteRook());

        return rank;
    }

    public static Rank createFirstBlackRank() {
        Rank rank = new Rank();

        rank.addPiece(Piece.createBlackRook());
        rank.addPiece(Piece.createBlackKnight());
        rank.addPiece(Piece.createBlackBishop());
        rank.addPiece(Piece.createBlackQueen());
        rank.addPiece(Piece.createBlackKing());
        rank.addPiece(Piece.createBlackBishop());
        rank.addPiece(Piece.createBlackKnight());
        rank.addPiece(Piece.createBlackRook());

        return rank;
    }

    public static Rank createRankWithOnlyWhitePawn() {
        Rank rank = new Rank();

        for (int file = 0; file < Board.MAX_FILE; file++) {
            rank.addPiece(Piece.createWhitePawn());
        }
        return rank;
    }

    public static Rank createRankWithOnlyBlackPawn() {
        Rank rank = new Rank();

        for (int file = 0; file < Board.MAX_FILE; file++) {
            rank.addPiece(Piece.createBlackPawn());
        }
        return rank;
    }

    public static Rank createBlankRank() {
        Rank rank = new Rank();

        for (int file = 0; file < Board.MAX_FILE; file++) {
            rank.addPiece(Piece.createBlank());
        }
        return rank;
    }

    public int pieceCount() {
        return (int) pieces.stream()
                .filter(piece -> !piece.isNoPiece())
                .count();
    }

    public int pieceCount(Piece.Color color, Piece.Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.checkTypeAndColor(color, type))
                .count();

    }

    public String getRankRepresentation() {
        StringBuilder sb = new StringBuilder();

        for (Piece piece : pieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }
}
