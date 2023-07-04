package chess.pieces;

public class Pawn {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';
    public static final char EMPTY_REPRESENTATION = '.';

    private String color;
    private char representation;

    public Pawn(String color) {
        this.color = color;
        this.representation = color.equals(WHITE_COLOR) ? WHITE_REPRESENTATION : BLACK_REPRESENTATION;
    }

    public Pawn() {
        this.color = WHITE_COLOR;
        this.representation = WHITE_REPRESENTATION;
    }

    public Pawn(String color, char representation) {
        this.color = color;
        this.representation = representation;
    }

    public String getColor() {
        return this.color;
    }

    public char getRepresentation() {
        return this.representation;
    }
}
