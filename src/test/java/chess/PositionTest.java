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

    @Test
    @DisplayName("같은 방향인지 여부를 반환해야 한다.")
    void isSameDirection() {
        // given
        Position sourcePosition = Position.of(0, 0);
        Position targetNEPosition1 = Position.of(1, 1);
        Position targetNEPosition2 = Position.of(2, 2);
        Position targetRandomPosition = Position.of(3,5);
        Position targetNPosition = Position.of(0, 1);
        Position targetSSEPosition = Position.getInstanceWithNoValidate(1, -2);

        // when
        boolean NETrue1 = targetNEPosition1.isSameDirection(Piece.Direction.NORTHEAST.getDegree(), sourcePosition);
        boolean NETrue2 = targetNEPosition2.isSameDirection(Piece.Direction.NORTHEAST.getDegree(), sourcePosition);
        boolean randomFalse = targetRandomPosition.isSameDirection(Piece.Direction.NORTHEAST.getDegree(), sourcePosition);
        boolean NFalse = targetNPosition.isSameDirection(Piece.Direction.NORTHEAST.getDegree(), sourcePosition);
        boolean NTrue = targetNPosition.isSameDirection(Piece.Direction.NORTH.getDegree(), sourcePosition);
        boolean SSEFalse = targetSSEPosition.isSameDirection(Piece.Direction.NORTHEAST.getDegree(), sourcePosition);
        boolean SSETrue = targetSSEPosition.isSameDirection(Piece.Direction.SSE.getDegree(), sourcePosition);

        assertTrue(NETrue1);
        assertTrue(NETrue2);
        assertFalse(randomFalse);
        assertFalse(NFalse);
        assertTrue(NTrue);
        assertFalse(SSEFalse);
        assertTrue(SSETrue);
    }
}
