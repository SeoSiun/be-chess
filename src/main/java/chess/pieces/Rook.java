package chess.pieces;

import java.util.Arrays;
import java.util.List;

import static chess.pieces.Piece.Direction.*;

public class Rook extends RecursivePiece {
    private static final List<Direction> directions = Arrays.asList(NORTH, EAST, SOUTH, WEST);
    protected Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
