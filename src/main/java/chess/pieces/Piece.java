package chess.pieces;

import java.util.Objects;

public class Piece {
    public enum Color {
        BLACK,
        WHITE,
        NO_COLOR
    }

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        NO_PIECE('.', 0.0);

        public static final double DUPLICATE_PAWN_POINT = 0.5;
        private final char representation;
        private final double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

        public double getDefaultPoint() {
            return defaultPoint;
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
        if (color == Color.BLACK) {
            return type.getBlackRepresentation();
        }
        return type.getWhiteRepresentation();
    }

    public double getPoint() {
        return type.getDefaultPoint();
    }

    public Boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public Boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public Boolean isBlank() {
        return this.type == Type.NO_PIECE;
    }

    public Boolean isPawn() {
        return this.type == Type.PAWN;
    }

    public boolean checkColorAndType(Color color, Type type) {
        return this.color == color && this.type == type;
    }

    public boolean checkColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
