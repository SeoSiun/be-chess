package chess;

public class Position {
    private static final String ILLEGAL_COORDINATE_ERROR = "잘못된 좌표 입니다.";
    private final int xPos;
    private final int yPos;

    private Position(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public static Position from(String coordinate) {
        validate(coordinate);

        return new Position(fileToIndex(coordinate.charAt(0)),rankToIndex(coordinate.charAt(1)));
    }

    public static Position of(int xPos, int yPos) {
        validate(xPos, yPos);

        return new Position(xPos, yPos);
    }

    private static void validate(String coordinate) {
        if (coordinate.length() != 2) {
            throw new IllegalArgumentException(ILLEGAL_COORDINATE_ERROR);
        }
        if (isInvalidRange(fileToIndex(coordinate.charAt(0)))) {
            throw new IllegalArgumentException(ILLEGAL_COORDINATE_ERROR);
        }
        if (isInvalidRange(rankToIndex(coordinate.charAt(1)))) {
            throw new IllegalArgumentException(ILLEGAL_COORDINATE_ERROR);
        }
    }

    private static void validate(int xPos, int yPos) {
        if (isInvalidRange(xPos)) {
            throw new IllegalArgumentException(ILLEGAL_COORDINATE_ERROR);
        }
        if (isInvalidRange(yPos)) {
            throw new IllegalArgumentException(ILLEGAL_COORDINATE_ERROR);
        }
    }

    private static boolean isInvalidRange(int pos) {
        return pos < 0 || pos > 7;
    }

    private static int fileToIndex(char file) {
        return file - 'a';
    }

    private static int rankToIndex(char num) {
        return Character.getNumericValue(num) - 1;
    }
    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
