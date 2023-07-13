package chess.pieces;

import chess.Board;
import chess.Position;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bishop, Queen, Rook
 */
public abstract class RecursivePiece extends Piece {
    public static final String UNREACHABLE_BY_OBSTACLE = "다른 기물에 의해 막혀있으므로 해당 위치로 이동할 수 없습니다.";
    public static final String INVALID_TARGET_POSITION = "해당 기물이 이동할 수 없는 위치입니다.";
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
            throw new IllegalArgumentException(INVALID_TARGET_POSITION);
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
            throw new IllegalArgumentException(UNREACHABLE_BY_OBSTACLE);
        }
        checkReachability(board, direction, targetPosition, curPosition.add(direction));
    }

    private boolean isSameDirection(Position direction, Position vector) {
        return vector.equals(direction) || direction.equals(vector.getUnitVector());
    }
}
