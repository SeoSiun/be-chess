package chess.pieces;

import java.util.Arrays;
import java.util.List;

import static chess.pieces.Piece.Direction.*;

public class Knight extends Piece {
    private static final List<Direction> directions = Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    protected Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public int getMaxMoveCount() {
        return 1;
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
