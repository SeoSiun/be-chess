package chess.pieces;

public class Blank extends Piece {
    protected Blank() {
        super(Color.NO_COLOR, Type.NO_PIECE, null);
    }

    @Override
    public int getMaxMoveCount() {
        return 0;
    }
}
