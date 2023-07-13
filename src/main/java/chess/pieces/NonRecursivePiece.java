package chess.pieces;

import chess.Board;
import chess.Position;
import exceptions.InvalidTargetPositionException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Pawn, Knight, King
 */
public abstract class NonRecursivePiece extends Piece {
    protected NonRecursivePiece(Color color, Type type) {
        super(color, type);
    }

    @Override
    public Direction checkMovable(Board board, Position sourcePosition, Position targetPosition) {
        Position vector = targetPosition.sub(sourcePosition);

        List<Direction> filteredDirection = getDirections().stream()
                .filter(direction -> direction.getDegree().equals(vector))
                .collect(Collectors.toList());

        if (filteredDirection.isEmpty()) {
            throw new InvalidTargetPositionException();
        }

        return filteredDirection.get(0);
    }
}
