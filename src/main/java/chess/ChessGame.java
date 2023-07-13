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
            return Piece.Type.PAWN.getDefaultPoint() / 2 * count;
        }
        return Piece.Type.PAWN.getDefaultPoint() * count;
    }
}
