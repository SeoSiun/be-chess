package chess.pieces;

public class Bishop extends Piece {
    public Bishop() {
        super(Type.BISHOP);
    }

    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }
}
