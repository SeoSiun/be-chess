package chess.pieces;

public class PieceFactory {
    private PieceFactory() {}

    public static Piece createPiece(Piece.Color color, Piece.Type type) {
        switch (type) {
            case PAWN:
                return new Pawn(color);
            case KNIGHT:
                return new Knight(color);
            case ROOK:
                return new Rook(color);
            case BISHOP:
                return new Bishop(color);
            case QUEEN:
                return new Queen(color);
            case KING:
                return new King(color);
            case NO_PIECE:
                return new Blank();
            default:
                throw new IllegalArgumentException("존재하지 않는 기물타입입니다.");
        }
    }

    public static Piece createBlank() {
        return createPiece(Piece.Color.NO_COLOR, Piece.Type.NO_PIECE);
    }
}
