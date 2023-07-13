package chess;

import exceptions.PositionOutOfRangeException;

import java.util.Objects;

public class Position {
    private final int xPos;
    private final int yPos;

    private Position(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public static Position from(String coordinate) {
        validate(coordinate);

        return new Position(fileToIndex(coordinate.charAt(0)), rankToIndex(coordinate.charAt(1)));
    }

    public static Position of(int xPos, int yPos) {
        validate(xPos, yPos);

        return new Position(xPos, yPos);
    }

    public static Position getInstanceWithNoValidate(int xPos, int yPos) {
        return new Position(xPos, yPos);
    }

    private static void validate(String coordinate) {
        if (coordinate.length() != 2) {
            throw new PositionOutOfRangeException();
        }
        if (isInvalidRange(fileToIndex(coordinate.charAt(0)))) {
            throw new PositionOutOfRangeException();
        }
        if (isInvalidRange(rankToIndex(coordinate.charAt(1)))) {
            throw new PositionOutOfRangeException();
        }
    }

    private static void validate(int xPos, int yPos) {
        if (isInvalidRange(xPos) || isInvalidRange(yPos)) {
            throw new PositionOutOfRangeException();
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

    public Position add(Position position) {
        return Position.of(xPos + position.xPos, yPos + position.yPos);
    }

    public Position sub(Position position) {
        return Position.getInstanceWithNoValidate(xPos - position.xPos, yPos - position.yPos);
    }

    public Position getUnitVector() {
        int newXPos = xPos;
        int newYPos = yPos;

        // 대각선, 수직, 수평 다 아닌 경우
        if (xPos != 0 && yPos != 0 && Math.abs(xPos) != Math.abs(yPos)) {
            return null;
        }
        if (xPos != 0) {
            newXPos = xPos / Math.abs(xPos);
        }
        if (yPos != 0) {
            newYPos = yPos / Math.abs(yPos);
        }
        return Position.getInstanceWithNoValidate(newXPos, newYPos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xPos == position.xPos && yPos == position.yPos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPos, yPos);
    }
}
