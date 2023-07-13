package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @Test
    @DisplayName("'a8'은 0,7이 되어야한다.")
    void create() {
        // given
        String coordinate = "a8";

        // when
        Position position = Position.from(coordinate);

        // then
        assertEquals(0, position.getXPos());
        assertEquals(7, position.getYPos());
    }

    @Test
    @DisplayName("좌표 범위를 벗어나면 PositionOutOfRangeException을 throw한다")
    void createPositionFromOutOfRange() {
        // given
        String coordinateWithRankOutOfRange = "a0";
        String coordinateWithFileOutOfRange = "k8";
        String coordinateWithLongLength = "a13";
        String coordinateWithCharRank = "aa";
        String coordinateWithIntFile = "11";

        // when & then
        verifyException(coordinateWithRankOutOfRange, Position.POSITION_OUT_OF_RANGE);
        verifyException(coordinateWithFileOutOfRange, Position.POSITION_OUT_OF_RANGE);
        verifyException(coordinateWithLongLength, Position.POSITION_OUT_OF_RANGE);
        verifyException(coordinateWithCharRank, Position.POSITION_OUT_OF_RANGE);
        verifyException(coordinateWithIntFile, Position.POSITION_OUT_OF_RANGE);
    }

    @Test
    @DisplayName("좌표 범위를 벗어나면 PositionOutOfRangeException을 throw한다")
    void createPositionOfOutOfRange() {
        // given
        int nine = 9;
        int negative = -1;
        int normal = 3;

        // when & then
        verifyException(normal, negative, Position.POSITION_OUT_OF_RANGE);
        verifyException(normal, nine, Position.POSITION_OUT_OF_RANGE);
        verifyException(negative, normal, Position.POSITION_OUT_OF_RANGE);
        verifyException(nine, negative, Position.POSITION_OUT_OF_RANGE);
    }

    private void verifyException(String coordinate, String expectedMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Position.from(coordinate));
        assertEquals(expectedMessage, exception.getMessage());
    }

    private void verifyException(int xPos, int yPos, String expectedMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Position.of(xPos, yPos));
        assertEquals(expectedMessage, exception.getMessage());
    }
}
