package chess.pieces;

public class Pawn extends Piece {
    public Pawn() {
        super(Type.PAWN);
    }

    public Pawn(Color color) {
        super(color, Type.PAWN);
    }
}
