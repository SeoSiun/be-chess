package chess.pieces;

public class King extends Piece {
    public King() {
        super(Type.KING);
    }

    public King(Color color) {
        super(color, Type.KING);
    }
}
