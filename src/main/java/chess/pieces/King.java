package chess.pieces;

import java.util.Arrays;
import java.util.List;

import static chess.pieces.Piece.Direction.*;

public class King extends NonRecursivePiece {
    private static final List<Direction> directions = Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    protected King(Color color) {
        super(color, Type.KING);
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
