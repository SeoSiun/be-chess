package chess;

import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.pieces.PieceFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 8 * 8 체스판 구성 및 관리
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
        ranks.add(RankFactory.createFirstRank(Color.WHITE));
        ranks.add(RankFactory.createRankWithOneColorAndType(Color.WHITE, Type.PAWN));
        for (int file = 2; file < MAX_FILE - 2; file++) {
            ranks.add(RankFactory.createBlankRank());
        }
        ranks.add(RankFactory.createRankWithOneColorAndType(Color.BLACK, Type.PAWN));
        ranks.add(RankFactory.createFirstRank(Color.BLACK));
    }

    public void initializeEmpty() {
        ranks.clear();
        for (int file = 0; file < MAX_FILE; file++) {
            ranks.add(RankFactory.createBlankRank());
        }
    }

    public void move(Position position, Piece piece) {
        ranks.get(position.getYPos()).setPiece(position.getXPos(), piece);
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece pieceToMove = findPiece(sourcePosition);

        move(sourcePosition, PieceFactory.createBlank());
        move(targetPosition, pieceToMove);

        // pawn은 첫 번째에만 2칸 움직일 수 있고, 이후에는 1칸만 움직일 수 있음
        if (pieceToMove instanceof Pawn) {
            ((Pawn) pieceToMove).afterFirstMove();
        }
    }

    /**
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
     * @param color : 찾을 기물 색상
     * @param type  : 찾을 기물 타입
     * @return : 해당 color, type의 기물 개수 반환
     */
    public int pieceCount(Color color, Type type) {
        int count = 0;

        for (Rank rank : ranks) {
            count += rank.pieceCount(color, type);
        }
        return count;
    }

    /**
     * @param coordinate : 찾을 좌표
     * @return : 해당 위치에 있는 기물 반환
     */
    public Piece findPiece(String coordinate) {
        Position position = Position.from(coordinate);

        return findPiece(position);
    }

    /**
     * @param rank : rank index
     * @param file : file index
     * @return : 해당 rank, file에 위치한 기물 반환
     */
    private Piece findPiece(int rank, int file) {
        Position position = Position.of(file, rank);

        return findPiece(position);
    }

    /**
     * @param position : 찾을 위치
     * @return : 해당 위치에 있는 기물 반환
     */
    private Piece findPiece(Position position) {
        return ranks.get(position.getYPos()).getPiece(position.getXPos());
    }

    public double calculatePoint(Color color) {
        double pointExceptPawn = ranks.stream()
                .mapToDouble(rank -> rank.calculatePointExceptPawn(color))
                .sum();

        return pointExceptPawn + calculatePawnPoint(color);
    }

    private double calculatePawnPoint(Color color) {
        return IntStream.range(0, MAX_FILE)
                .map(file -> (int) IntStream.range(0, MAX_RANK)
                        .filter(rank -> findPiece(rank, file).checkColorAndType(color, Type.PAWN)).count())
                .mapToDouble(Board::getPawnPointByCount).sum();
    }

    private static double getPawnPointByCount(int count) {
        if (count > 1) {
            return Type.PAWN.getDefaultPoint() / 2 * count;
        }
        return Type.PAWN.getDefaultPoint() * count;
    }

    public List<Piece> getSortedPiecesByPoint(Color color) {
        return ranks.stream()
                .flatMap(rank -> rank.getPiecesByColor(color).stream())
                .sorted(Comparator.comparingDouble(Piece::getPoint).reversed())
                .collect(Collectors.toList());
    }

    // TODO: 체스뷰?
    public String getRankRepresentation(int rank) {
        return ranks.get(rank).getRepresentation();
    }

    public boolean isBlank(Position position) {
        return findPiece(position).isBlank();
    }

    public boolean isSameColor(Position sourcePosition, Position targetPosition) {
        return findPiece(sourcePosition).isSameColor(findPiece(targetPosition));
    }

    public List<Piece.Direction> getDirections(Position sourcePosition) {
        return findPiece(sourcePosition).getDirections();
    }

    public boolean isWhite(Position position) {
        return findPiece(position).isWhite();
    }

    public boolean isBlack(Position position) {
        return findPiece(position).isBlack();
    }

    public void checkMovable(Position sourcePosition, Position targetPosition) {
        findPiece(sourcePosition).checkMovable(this, sourcePosition, targetPosition);
    }
}
