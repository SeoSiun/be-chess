package chess.pieces;

import java.util.Arrays;

import static chess.pieces.Piece.Direction.*;

public class Rook extends Piece {
    protected Rook(Color color) {
        super(color, Type.ROOK, Arrays.asList(NORTH, EAST, SOUTH, WEST));
    }
}
