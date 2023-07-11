package chess.pieces;

import java.util.Arrays;

import static chess.pieces.Piece.Direction.*;

public class Bishop extends Piece {
    protected Bishop(Color color) {
        super(color, Type.BISHOP, Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST));
    }
}
