package chess.pieces;

public class Knight extends Piece {
    protected Knight() {
        super(Type.KNIGHT);
    }

    protected Knight(Color color) {
        super(color, Type.KNIGHT);
    }
}
