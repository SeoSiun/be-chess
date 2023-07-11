package chess.pieces;

import java.util.Arrays;

import static chess.pieces.Piece.Direction.*;

public class Pawn extends Piece {
    int maxMoveCount = 2;
    protected Pawn(Color color) {
        super(color, Type.PAWN, color == Color.WHITE ? Arrays.asList(NORTH, NORTHEAST, NORTHWEST) : Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST));
    }

    @Override
    public int getMaxMoveCount() {
        return this.maxMoveCount;
    }

    public void afterFirstMove() {
        maxMoveCount = 1;
    }
}
