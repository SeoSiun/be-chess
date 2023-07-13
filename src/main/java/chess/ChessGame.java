package chess;

import chess.pieces.Piece;
import exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 체스 규칙에 따른 로직
 */
public class ChessGame {
    public void move(Board board, String sourceCoordinate, String targetCoordinate, Piece.Color turn) {
        Position sourcePosition = Position.from(sourceCoordinate);
        Position targetPosition = Position.from(targetCoordinate);

        validate(board, sourcePosition, targetPosition, turn);

        board.move(sourcePosition, targetPosition);
    }

    private void validate(Board board, Position sourcePosition, Position targetPosition, Piece.Color turn) {
        checkNoPieceInSource(board, sourcePosition);
        checkValidTurn(board, turn, sourcePosition);
        checkTargetSameAsSource(sourcePosition, targetPosition);
        Piece.Direction direction = getDirection(board, sourcePosition, targetPosition);
        checkIsTargetSameColor(board, sourcePosition, targetPosition);
        checkTargetReachable(board, sourcePosition, targetPosition, direction);
    }

    private void checkValidTurn(Board board, Piece.Color turn, Position sourcePosition) {
        if (turn == Piece.Color.WHITE && board.isWhite(sourcePosition)) {
            return;
        }
        if (turn == Piece.Color.BLACK && board.isBlack(sourcePosition)) {
            return;
        }
        throw new InvalidTurnException();
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

    /**
     * source-target이 어느 방향인지 반환, 올바르지 않은 방향이면 예외처리
     */
    private Piece.Direction getDirection(Board board, Position sourcePosition, Position targetPosition) {
        List<Piece.Direction> filteredDirection = board.getDirections(sourcePosition).stream()
                .filter(direction -> targetPosition.isSameDirection(direction.getDegree(), sourcePosition))
                .collect(Collectors.toList());

        if (filteredDirection.isEmpty()) {
            throw new InvalidTargetPositionException();
        }
        return filteredDirection.get(0);
    }

    /**
     * target에 도달 가능하지 않다면 예외처리 (경로에 다른 기물이 존재하는 경우)
     */
    private void checkTargetReachable(Board board, Position sourcePosition, Position targetPosition, Piece.Direction direction) {
        // pawn이 대각선 방향으로 이동할 때 상대편 기물이 존재하지 않는 경우 예외처리
        if (board.isPawn(sourcePosition) && (isDiagonal(direction)) && (board.isBlank(targetPosition))) {
            throw new PawnMoveDiagonalWithNoEnemyException();
        }
        checkReachability(board, direction.getDegree(), targetPosition, sourcePosition.add(direction.getDegree()));
    }

    private boolean isDiagonal(Piece.Direction direction) {
        return direction == Piece.Direction.NORTHEAST || direction == Piece.Direction.NORTHWEST
                || direction == Piece.Direction.SOUTHEAST || direction == Piece.Direction.SOUTHWEST;
    }

    private void checkReachability(Board board, Position direction, Position targetPosition, Position curPosition) {
        if (targetPosition.equals(curPosition)) {
            return;
        }
        if (!board.isBlank(curPosition)) {
            // source - target 경로에 다른 기물이 존재하는 경우 예외 처리
            throw new UnreachableWithObstacleException();
        }
        checkReachability(board, direction, targetPosition, curPosition.add(direction));
    }

}
