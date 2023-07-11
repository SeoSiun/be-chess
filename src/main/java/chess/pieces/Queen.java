package chess.pieces;

import java.util.Arrays;

import static chess.pieces.Piece.Direction.*;

public class Queen extends Piece {
    protected Queen(Color color) {
        super(color, Type.QUEEN, Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST));
    }
}
