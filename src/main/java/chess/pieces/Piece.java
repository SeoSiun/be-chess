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

    public static Piece createBlank() {
        return createPiece(Color.NO_COLOR, Type.NO_PIECE);
    }

    public static Piece createPiece(Color color, Type type) {
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

}
