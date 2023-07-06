package chess;

public class Position {
    private int xPos;
    private int yPos;

    public Position(String coordinate) {
        this.xPos = fileToIndex(coordinate.charAt(0));
        this.yPos = rankToIndex(coordinate.charAt(1));
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int fileToIndex(char file) {
        return file - 'a';
    }

    public int rankToIndex(char num) {
        return Character.getNumericValue(num) - 1;
    }

}
