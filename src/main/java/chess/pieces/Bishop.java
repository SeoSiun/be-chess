package chess.pieces;

import java.util.Arrays;
import java.util.List;

import static chess.pieces.Piece.Direction.*;

public class Bishop extends RecursivePiece {
    private static final List<Direction> directions = Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);

    protected Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
