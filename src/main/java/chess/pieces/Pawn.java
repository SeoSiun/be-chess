package chess.pieces;

public class Pawn extends Piece {
    protected Pawn() {
        super(Type.PAWN);
    }

    protected Pawn(Color color) {
        super(color, Type.PAWN);
    }
}
