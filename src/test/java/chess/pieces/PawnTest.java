package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PawnTest {
    @Test
    @DisplayName("흰색/검은색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Pawn.WHITE_COLOR);
        verifyPawn(Pawn.BLACK_COLOR);
    }

    void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    @DisplayName("색이 주어지지 않으면 희색 폰이 생성되어야 한다.")
    public void create_기본생성자(){
        Pawn pawn = new Pawn();
        assertEquals("white", pawn.getColor());
        assertEquals(Pawn.WHITE_REPRESENTATION, pawn.getRepresentation());
    }

    @Test
    @DisplayName("흰색은 p, 검은색은 P로 표기되어야 한다.")
    public void create_with_representation() {
        verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    void verifyPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }
}
