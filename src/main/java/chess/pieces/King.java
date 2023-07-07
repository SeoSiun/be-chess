package chess.pieces;

public class King extends Piece {
    protected King() {
        super(Type.KING);
    }

    protected King(Color color) {
        super(color, Type.KING);
    }
}
