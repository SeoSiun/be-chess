package chess.pieces;

import java.util.Arrays;
import java.util.List;

import static chess.pieces.Piece.Direction.*;

public class Pawn extends Piece {
    private boolean isFirst;
    private static final List<Direction> white_directions = Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    private static final List<Direction> black_directions = Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);

    protected Pawn(Color color) {
        super(color, Type.PAWN);
        isFirst = true;
    }

    @Override
    public int getMaxMoveCount() {
        if (isFirst) {
            return 2;
        }
        return 1;
    }

    public void afterFirstMove() {
        isFirst = false;
    }

    @Override
    public List<Direction> getDirections() {
        if (isWhite()) {
            return white_directions;
        }
        return black_directions;
    }
}
