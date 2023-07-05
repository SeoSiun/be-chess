package chess.pieces;

public class Queen extends Piece {
    public Queen() {
        super(Type.QUEEN);
    }

    public Queen(Color color) {
        super(color, Type.QUEEN);
    }
}
