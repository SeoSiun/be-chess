package chess.pieces;

public class Piece {
    public enum Color {
        BLACK,
        WHITE,
        NO_COLOR
    }

    public enum Type {
        PAWN('p'),
        KNIGHT('n'),
        ROOK('r'),
        BISHOP('b'),
        QUEEN('q'),
        KING('k'),
        NO_PIECE('.');

        private final char representation;

        Type(char representation) {
            this.representation = representation;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }
    }

    private final Color color;
    private final Type type;

    protected Piece() {
        this.color = Color.NO_COLOR;
        this.type = Type.NO_PIECE;
    }

    protected Piece(Type type) {
        this.color = Color.WHITE;
        this.type = type;
    }

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    public char getRepresentation() {
        if (color == Color.WHITE || color == Color.NO_COLOR) {
            return type.getWhiteRepresentation();
        }
        return type.getBlackRepresentation();
    }

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }

    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
    }

    public static Piece createWhiteBishop() {
        return createWhite(Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return createBlack(Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }

    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    public static Piece createBlank() {
        return createPiece(Color.NO_COLOR, Type.NO_PIECE);
    }

    private static Piece createWhite(Type type) {
        return createPiece(Color.WHITE, type);
    }

    private static Piece createBlack(Type type) {
        return createPiece(Color.BLACK, type);
    }

    private static Piece createPiece(Color color, Type type) {
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
                throw new IllegalArgumentException("this type is not allowed");
        }
    }

    public Boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public Boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public Boolean isNoPiece() {
        return this.type == Type.NO_PIECE;
    }

    public boolean checkTypeAndColor(Color color, Type type) {
        return this.color == color && this.type == type;
    }
}
