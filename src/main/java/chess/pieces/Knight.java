package chess.pieces;

import java.util.Arrays;

import static chess.pieces.Piece.Direction.*;

public class Knight extends Piece {
    protected Knight(Color color) {
        super(color, Type.KNIGHT, Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS));
    }

    @Override
    public int getMaxMoveCount() {
        return 1;
    }
}
