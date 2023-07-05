package chess.pieces;

public class Knight extends Piece {
    public Knight() {
        super(Type.KNIGHT);
    }

    public Knight(Color color) {
        super(color, Type.KNIGHT);
    }
}
