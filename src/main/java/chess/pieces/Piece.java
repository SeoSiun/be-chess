package chess.pieces;

import chess.Board;
import chess.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
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

    public enum Direction {
        NORTH(0, 1),
        NORTHEAST(1, 1),
        EAST(1, 0),
        SOUTHEAST(1, -1),
        SOUTH(0, -1),
        SOUTHWEST(-1, -1),
        WEST(-1, 0),
        NORTHWEST(-1, 1),

        NN(0, 2),
        SS(0, -2),

        NNE(1, 2),
        NNW(-1, 2),
        SSE(1, -2),
        SSW(-1, -2),
        EEN(2, 1),
        EES(2, -1),
        WWN(-2, 1),
        WWS(-2, -1);

        private final Position degree;

        private Direction(int xDegree, int yDegree) {
            this.degree = Position.getInstanceWithNoValidate(xDegree, yDegree);
        }

        public Position getDegree() {
            return degree;
        }
    }

    private final Color color;
    private final Type type;

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

    public abstract List<Direction> getDirections();

    public abstract Direction checkMovable(Board board, Position sourcePosition, Position targetPosition);

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

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public boolean checkColorAndType(Color color, Type type) {
        return this.color == color && this.type == type;
    }

    public boolean checkColor(Color color) {
        return this.color == color;
    }

    public boolean isKing() {
        return this.type == Type.KING;
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
