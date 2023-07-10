package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  8 * 8 체스판 구성 및 관리
 */
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

    public void move(Position position, Piece piece) {
        ranks.get(position.getYPos()).setPiece(position.getXPos(), piece);
    }

    /**
     *
     * @return : 전체 기물 개수 반환
     */
    public int pieceCount() {
        int count = 0;

        for (Rank rank : ranks) {
            count += rank.pieceCount();
        }
        return count;
    }

    /**
     *
     * @param color : 찾을 기물 색상
     * @param type : 찾을 기물 타입
     * @return : 해당 color, type의 기물 개수 반환
     */
    public int pieceCount(Piece.Color color, Piece.Type type) {
        int count = 0;

        for (Rank rank : ranks) {
            count += rank.pieceCount(color, type);
        }
        return count;
    }

    /**
     *
     * @param coordinate : 찾을 좌표
     * @return : 해당 위치에 있는 기물 반환
     */
    public Piece findPiece(String coordinate) {
        Position position = Position.from(coordinate);

        return findPiece(position);
    }

    /**
     *
     * @param rank : rank index
     * @param file : file index
     * @return : 해당 rank, file에 위치한 기물 반환
     */
    private Piece findPiece(int rank, int file) {
        Position position = Position.of(file, rank);

        return findPiece(position);
    }

    /**
     *
     * @param position : 찾을 위치
     * @return : 해당 위치에 있는 기물 반환
     */
    private Piece findPiece(Position position) {
        return ranks.get(position.getYPos()).getPiece(position.getXPos());
    }

    public double calculatePoint(Piece.Color color) {
        double pointExceptPawn = ranks.stream()
                .mapToDouble(rank -> rank.calculatePointExceptPawn(color))
                .sum();

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

    public String getRankRepresentation(int rank) {
        return ranks.get(rank).getRepresentation();
    }
}
