package chess.pieces;

import chess.Board;
import chess.Position;
import exceptions.PawnMoveDiagonalWithNoEnemyException;
import exceptions.UnreachableWithObstacleException;

import java.util.Arrays;
import java.util.List;

import static chess.pieces.Piece.Direction.*;

public class Pawn extends NonRecursivePiece {
    private boolean isFirst;
    private static final List<Direction> WHITE_DIRECTIONS = Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    private static final List<Direction> WHITE_FIRST_DIRECTIONS = Arrays.asList(NORTH, NN, NORTHEAST, NORTHWEST);
    private static final List<Direction> BLACK_DIRECTIONS = Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    private static final List<Direction> BLACK_FIRST_DIRECTIONS = Arrays.asList(SOUTH, SS, SOUTHEAST, SOUTHWEST);

    protected Pawn(Color color) {
        super(color, Type.PAWN);
        isFirst = true;
    }

    public void afterFirstMove() {
        isFirst = false;
    }

    @Override
    public List<Direction> getDirections() {
        if (isWhite()) {
            if (isFirst) {
                return WHITE_FIRST_DIRECTIONS;
            }
            return WHITE_DIRECTIONS;
        }

        if (isFirst) {
            return BLACK_FIRST_DIRECTIONS;
        }
        return BLACK_DIRECTIONS;
    }

    /**
     * 폰의 첫 번째 이동, 대각선 방향 이동의 예외 처리를 위해 오버라이딩
     */
    @Override
    public Direction checkMovable(Board board, Position sourcePosition, Position targetPosition) {
        Direction direction = super.checkMovable(board, sourcePosition, targetPosition);

        // pawn이 대각선 방향으로 이동할 때 상대편 기물이 존재하지 않는 경우 예외처리
        if (isDiagonal(direction) && board.isBlank(targetPosition)) {
            throw new PawnMoveDiagonalWithNoEnemyException();
        }

        // 첫 번째 이동 - 두 칸까지 이동시 장애물이 있으면 예외처리
        if (isFirst && isTwoStep(direction)) {
            if (!board.isBlank(sourcePosition.add(direction.getDegree().getUnitVector()))) {
                throw new UnreachableWithObstacleException();
            }
        }
        return direction;
    }

    private boolean isDiagonal(Piece.Direction direction) {
        return direction == Piece.Direction.NORTHEAST || direction == Piece.Direction.NORTHWEST
                || direction == Piece.Direction.SOUTHEAST || direction == Piece.Direction.SOUTHWEST;
    }

    private boolean isTwoStep(Direction direction) {
        return direction == NN || direction == SS;
    }
}
