package chess.pieces;

import java.util.List;

public class Blank extends Piece {
    protected Blank() {
        super(Color.NO_COLOR, Type.NO_PIECE);
    }

    @Override
    public int getMaxMoveCount() {
        return 0;
    }

    @Override
    public List<Direction> getDirections() {
        return null;
    }
}
