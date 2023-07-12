package chess.pieces;

import java.util.Arrays;
import java.util.List;

import static chess.pieces.Piece.Direction.*;

public class Queen extends Piece {
    private static final List<Direction> directions = Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    protected Queen(Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
