package chess;

import chess.pieces.Piece;
import exceptions.PositionOutOfRangeException;
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
    void createPositionOutOfRange() {
        // given
        String coordinateWithRankOutOfRange = "a0";
        String coordinateWithFileOutOfRange = "k8";
        String coordinateWithLongLength = "a13";
        String coordinateWithCharRank = "aa";
        String coordinateWithIntFile = "11";

        // when & then
        assertThrows(PositionOutOfRangeException.class, () -> Position.from(coordinateWithRankOutOfRange));
        assertThrows(PositionOutOfRangeException.class, () -> Position.from(coordinateWithFileOutOfRange));
        assertThrows(PositionOutOfRangeException.class, () -> Position.from(coordinateWithLongLength));
        assertThrows(PositionOutOfRangeException.class, () -> Position.from(coordinateWithCharRank));
        assertThrows(PositionOutOfRangeException.class, () -> Position.from(coordinateWithIntFile));
    }
}
