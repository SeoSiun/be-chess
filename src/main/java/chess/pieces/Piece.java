package chess.pieces;

import chess.Position;

import java.util.List;
import java.util.Objects;

import static chess.Board.MAX_RANK;

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

        public boolean isNEorNW() {
            return this == Direction.NORTHEAST  || this == Direction.NORTHWEST;
        }
    }

    private final Color color;
    private final Type type;
    private final List<Direction> directions;

    protected Piece(Color color, Type type, List<Direction> directions) {
        this.color = color;
        this.type = type;
        this.directions = directions;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public int getMaxMoveCount() {
        return MAX_RANK;
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

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
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
