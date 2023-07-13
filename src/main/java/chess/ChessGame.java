package chess;

import chess.pieces.Piece;
import exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 체스 규칙에 따른 로직
 */
public class ChessGame {
    public void move(Board board, String sourceCoordinate, String targetCoordinate) {
        Position sourcePosition = Position.from(sourceCoordinate);
        Position targetPosition = Position.from(targetCoordinate);

        validate(board, sourcePosition, targetPosition);
        board.move(sourcePosition, targetPosition);
    }

    private void validate(Board board, Position sourcePosition, Position targetPosition) {
        checkNoPieceInSource(board, sourcePosition);
        checkTargetSameAsSource(sourcePosition, targetPosition);
        checkIsTargetSameColor(board, sourcePosition, targetPosition);
        board.checkMovable(sourcePosition, targetPosition);
    }

    /**
     * source에 기물이 없는 경우 예외처리
     */
    private void checkNoPieceInSource(Board board, Position sourcePosition) {
        if (board.isBlank(sourcePosition)) {
            throw new NoPieceInSourceException();
        }
    }

    /**
     * source와 target이 같은 위치인 경우 예외처리
     */
    private void checkTargetSameAsSource(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new TargetSameAsSourceException();
        }
    }

    /**
     * source에 있는 기물과 target에 있는 기물의 색이 같은 경우 예외처리
     */
    private void checkIsTargetSameColor(Board board, Position sourcePosition, Position targetPosition) {
        if (board.isSameColor(sourcePosition, targetPosition)) {
            throw new TargetSameColorException();
        }
    }
}
