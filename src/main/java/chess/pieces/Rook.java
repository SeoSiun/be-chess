package chess.pieces;

public class Rook extends Piece {
    public Rook() {
        super(Type.ROOK);
    }

    public Rook(Color color) {
        super(color, Type.ROOK);
    }
}
