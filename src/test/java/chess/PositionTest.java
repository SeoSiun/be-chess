package chess;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    @DisplayName("'a8'은 0,7이 되어야한다.")
    public void create() {
        Position position = new Position("a8");

        assertEquals(0, position.getXPos());
        assertEquals(7, position.getYPos());
    }
}
