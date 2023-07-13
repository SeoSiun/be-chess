package chess.pieces;

import chess.Board;
import chess.Position;

import java.util.Collections;
import java.util.List;

public class Blank extends Piece {
    protected Blank() {
        super(Color.NO_COLOR, Type.NO_PIECE);
    }

    @Override
    public List<Direction> getDirections() {
        return Collections.emptyList();
    }

    @Override
    public Direction checkMovable(Board board, Position sourcePosition, Position targetPosition) {
        throw new IllegalArgumentException("빈칸은 이동시킬 수 없습니다.");
    }
}
