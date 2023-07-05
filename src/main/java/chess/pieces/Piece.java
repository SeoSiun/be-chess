package chess.pieces;

public class Piece {
    private final Color color;
    private final Type name;

    protected Piece() {
        this.color = Color.NONE;
        this.name = Type.NONE;
    }

    protected Piece(Type name) {
        this.color = Color.WHITE;
        this.name = name;
    }

    protected Piece(Color color, Type name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return this.color;
    }

    public char getRepresentation() {
        char r = name.getRepresentation();

        return isWhite() ? r : Character.toUpperCase(r);
    }

    public static Pawn createWhitePawn() {
        return new Pawn(Color.WHITE);
    }

    public static Pawn createBlackPawn() {
        return new Pawn(Color.BLACK);
    }

    public static Knight createWhiteKnight() {
        return new Knight(Color.WHITE);
    }

    public static Knight createBlackKnight() {
        return new Knight(Color.BLACK);
    }

    public static Rook createWhiteRook() {
        return new Rook(Color.WHITE);
    }

    public static Rook createBlackRook() {
        return new Rook(Color.BLACK);
    }

    public static Bishop createWhiteBishop() {
        return new Bishop(Color.WHITE);
    }

    public static Bishop createBlackBishop() {
        return new Bishop(Color.BLACK);
    }

    public static Queen createWhiteQueen() {
        return new Queen(Color.WHITE);
    }

    public static Queen createBlackQueen() {
        return new Queen(Color.BLACK);
    }

    public static King createWhiteKing() {
        return new King(Color.WHITE);
    }

    public static King createBlackKing() {
        return new King(Color.BLACK);
    }

    public static Piece createPiece(Color color, Type name) {
        switch (name) {
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
            case NONE:
                return new Piece();
            default:
                throw new IllegalArgumentException("this type is not allowed");
        }
    }

    public Boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public Boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public Boolean isNone() {
        return this.color == Color.NONE;
    }
}
