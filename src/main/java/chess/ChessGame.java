package chess;

import chess.pieces.Piece;
import exceptions.*;

import java.util.stream.IntStream;

import static chess.Board.MAX_FILE;
import static chess.Board.MAX_RANK;

/**
 * 체스 규칙에 따른 로직
 */
public class ChessGame {
    // 같은 file에 여러 개의 pawn이 존재하면 0.5점 적용
    private static final double DUPLICATED_PAWN_POINT = 0.5;

    public void move(Board board, String sourceCoordinate, String targetCoordinate) {
        Position sourcePosition = Position.from(sourceCoordinate);
        Position targetPosition = Position.from(targetCoordinate);

        validate(board, sourcePosition, targetPosition);
        board.move(sourcePosition, targetPosition);
    }

    private void validate(Board board, Position sourcePosition, Position targetPosition) {
        checkTargetSameAsSource(sourcePosition, targetPosition);
        checkIsTargetSameColor(board, sourcePosition, targetPosition);
        board.checkMovable(sourcePosition, targetPosition);
    }

    /**
     * source와 target이 같은 위치인 경우 예외처리
     */
    private void checkTargetSameAsSource(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new TargetSameAsSourceException();
        }
    }

    /**
     * source에 있는 기물과 target에 있는 기물의 색이 같은 경우 예외처리
     */
    private void checkIsTargetSameColor(Board board, Position sourcePosition, Position targetPosition) {
        if (board.isSameColor(sourcePosition, targetPosition)) {
            throw new TargetSameColorException();
        }
    }

    public double calculatePoint(Board board, Piece.Color color) {
        double pointExceptPawn = board.getRanks().stream()
                .mapToDouble(rank -> rank.calculatePointExceptPawn(color))
                .sum();

        return pointExceptPawn + calculatePawnPoint(board, color);
    }

    private double calculatePawnPoint(Board board, Piece.Color color) {
        return IntStream.range(0, MAX_FILE)
                .map(file -> countPawnInFile(board, file, color))
                .mapToDouble(ChessGame::getPawnPointByCount).sum();
    }

    private int countPawnInFile(Board board, int file, Piece.Color color) {
        return (int) IntStream.range(0, MAX_RANK)
                .filter(rank -> board.isPawn(file, rank, color)).count();
    }

    private static double getPawnPointByCount(int count) {
        if (count > 1) {
            return DUPLICATED_PAWN_POINT * count;
        }
        return Piece.Type.PAWN.getDefaultPoint() * count;
    }
}
