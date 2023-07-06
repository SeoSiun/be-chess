package chess.pieces;

public class Rook extends Piece {
    protected Rook() {
        super(Type.ROOK);
    }

    protected Rook(Color color) {
        super(color, Type.ROOK);
    }
}
