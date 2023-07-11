package chess.pieces;

import java.util.Arrays;

import static chess.pieces.Piece.Direction.*;

public class Pawn extends Piece {
    private boolean isFirst;
    protected Pawn(Color color) {
        super(color, Type.PAWN, color == Color.WHITE ? Arrays.asList(NORTH, NORTHEAST, NORTHWEST) : Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST));
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
}
