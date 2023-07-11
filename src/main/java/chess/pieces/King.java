package chess.pieces;

import java.util.Arrays;

import static chess.pieces.Piece.Direction.*;

public class King extends Piece {
    protected King(Color color) {
        super(color, Type.KING, Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST));
    }

    @Override
    public int getMaxMoveCount() {
        return 1;
    }
}
