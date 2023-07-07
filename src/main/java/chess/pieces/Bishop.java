package chess.pieces;

public class Bishop extends Piece {
    protected Bishop() {
        super(Type.BISHOP);
    }

    protected Bishop(Color color) {
        super(color, Type.BISHOP);
    }
}
