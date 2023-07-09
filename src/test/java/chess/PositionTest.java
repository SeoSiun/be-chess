package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @DisplayName("좌표 범위를 벗어나면 IllegalArgumentException 에러를 throw한다.")
    void createPositionOutOfRange() {
        // given
        String coordinateWithRankOutOfRange = "a0";
        String coordinateWithFileOutOfRange = "k8";
        String coordinateWithLongLength = "a8k";
        String coordinateWithCharRank = "aa";
        String coordinateWithIntFile = "11";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Position.from(coordinateWithRankOutOfRange));
        assertThrows(IllegalArgumentException.class, () -> Position.from(coordinateWithFileOutOfRange));
        assertThrows(IllegalArgumentException.class, () -> Position.from(coordinateWithLongLength));
        assertThrows(IllegalArgumentException.class, () -> Position.from(coordinateWithCharRank));
        assertThrows(IllegalArgumentException.class, () -> Position.from(coordinateWithIntFile));
    }
}
