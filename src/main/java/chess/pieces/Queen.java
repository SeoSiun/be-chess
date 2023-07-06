package chess.pieces;

public class Queen extends Piece {
    protected Queen() {
        super(Type.QUEEN);
    }

    protected Queen(Color color) {
        super(color, Type.QUEEN);
    }
}
