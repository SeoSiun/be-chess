package chess.pieces;

import chess.Board;
import chess.Position;
import exceptions.InvalidTargetPositionException;
import exceptions.NoIntegerException;
import exceptions.UnreachableWithObstacleException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bishop, Queen, Rook
 */
public abstract class RecursivePiece extends Piece {
    protected RecursivePiece(Color color, Type type) {
        super(color, type);
    }

    @Override
    public Direction checkMovable(Board board, Position sourcePosition, Position targetPosition) {
        Position vector = targetPosition.sub(sourcePosition);

        List<Piece.Direction> filteredDirection = getDirections().stream()
                .filter(direction -> isSameDirection(direction.getDegree(), vector))
                .collect(Collectors.toList());

        if (filteredDirection.isEmpty()) {
            throw new InvalidTargetPositionException();
        }

        Direction direction = filteredDirection.get(0);
        checkReachability(board, direction.getDegree(), targetPosition, sourcePosition.add(direction.getDegree()));

        return direction;
    }

    private void checkReachability(Board board, Position direction, Position targetPosition, Position curPosition) {
        if (targetPosition.equals(curPosition)) {
            return;
        }
        // source - target 경로에 다른 기물이 존재하는 경우 예외 처리
        if (!board.isBlank(curPosition)) {
            throw new UnreachableWithObstacleException();
        }
        checkReachability(board, direction, targetPosition, curPosition.add(direction));
    }

    private boolean isSameDirection(Position direction, Position vector) {
        try {
            return vector.equals(direction) || direction.equals(vector.getUnitVector());
        } catch (NoIntegerException exception) {
            return false;
        }
    }
}
