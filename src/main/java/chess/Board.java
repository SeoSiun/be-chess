package chess;

import chess.pieces.Piece;

import static utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private final List<Rank> ranks;

    public static final int MAX_RANK = 8;
    public static final int MAX_FILE = 8;

    public Board() {
        this.ranks = new ArrayList<>();
    }

    public void initialize() {
        ranks.clear();
        ranks.add(Rank.createFirstWhiteRank());
        ranks.add(Rank.createRankWithOnlyWhitePawn());
        for (int file = 2; file < MAX_FILE - 2; file++) {
            ranks.add(Rank.createBlankRank());
        }
        ranks.add(Rank.createRankWithOnlyBlackPawn());
        ranks.add(Rank.createFirstBlackRank());
    }

    public void initializeEmpty() {
        ranks.clear();
        for (int file = 0; file < MAX_FILE; file++) {
            ranks.add(Rank.createBlankRank());
        }
    }

    public void move(String coordinate, Piece piece) {
        move(Position.from(coordinate), piece);
    }

    public void move(String sourcePosition, String targetPosition) {
        Piece pieceToMove = findPiece(sourcePosition);

        move(sourcePosition, Piece.createBlank());
        move(targetPosition, pieceToMove);
    }

    private void move(Position position, Piece piece) {
        ranks.get(position.getYPos()).setPiece(position.getXPos(), piece);
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

        for (int rank = MAX_RANK; rank > 0; rank--) {
            sb.append(ranks.get(rank - 1).getRepresentation());
            sb.append(appendNewLine("  " + rank));
        }
        sb.append(appendNewLine(""));
        sb.append(appendNewLine("abcdefgh"));

        return sb.toString();
    }

    public Piece findPiece(String coordinate) {
        Position position = Position.from(coordinate);

        return findPiece(position);
    }

    private Piece findPiece(int rank, int file) {
        return ranks.get(rank).getPiece(file);
    }

    private Piece findPiece(Position position) {
        return ranks.get(position.getYPos()).getPiece(position.getXPos());
    }

    public double calculatePoint(Piece.Color color) {
        double pointExceptPawn = ranks.stream().mapToDouble(rank -> rank.calculatePointExceptPawn(color)).sum();

        return pointExceptPawn + calculatePawnPoint(color);
    }

    private double calculatePawnPoint(Piece.Color color) {
        return IntStream.range(0, MAX_FILE)
                .map(file -> (int) IntStream.range(0, MAX_RANK)
                        .filter(rank -> findPiece(rank, file).checkColorAndType(color, Piece.Type.PAWN)).count())
                .mapToDouble(Board::getPawnPointByCount).sum();
    }

    private static double getPawnPointByCount(int count) {
        if (count > 1) {
            return Piece.Type.DUPLICATE_PAWN_POINT * count;
        }
        return Piece.Type.PAWN.getDefaultPoint() * count;
    }

    public List<Piece> getSortedPiecesByPoint(Piece.Color color) {
        return ranks.stream()
                .flatMap(rank -> rank.getPiecesByColor(color).stream())
                .sorted(Comparator.comparingDouble(Piece::getPoint).reversed())
                .collect(Collectors.toList());
    }
}
