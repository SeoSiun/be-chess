package chess;

import chess.pieces.Piece;
import exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 체스 규칙에 따른 로직
 */
public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    /**
     * 해당 기물을 해당 좌표에 놓음
     *
     * @param coordinate : 기물을 놓을 위치
     * @param piece      : 놓을 기물
     */
    public void move(String coordinate, Piece piece) {
        board.move(Position.from(coordinate), piece);
    }

    /**
     * source에 있는 기물을 target으로 옮김
     *
     * @param sourcePosition : 옮기기 전 위치
     * @param targetPosition : 옮길 위치
     */
    public void move(String sourcePosition, String targetPosition, boolean isWhiteTurn) {
        validate(sourcePosition, targetPosition, isWhiteTurn);

        board.move(sourcePosition, targetPosition);
    }

    private void validate(String sourcePosition, String targetPosition, boolean isWhiteTurn) {
        checkNoPieceInSource(sourcePosition);
        checkValidTurn(isWhiteTurn, sourcePosition);
        checkTargetSameAsSource(sourcePosition, targetPosition);
        Piece.Direction direction = getDirection(Position.from(sourcePosition), Position.from(targetPosition));
        checkIsTargetSameColor(sourcePosition, targetPosition);
        checkTargetReachable(Position.from(sourcePosition), Position.from(targetPosition), direction);
    }

    private void checkValidTurn(boolean isWhiteTurn, String sourcePosition) {
        if (isWhiteTurn && board.isWhite(sourcePosition)) {
            return;
        }
        if (!isWhiteTurn && board.isBlack(sourcePosition)) {
            return;
        }
        throw new InvalidTurnException();
    }

    /**
     * source에 기물이 없는 경우 예외처리
     * @param sourcePosition : 옮길 기물의 위치
     */
    private void checkNoPieceInSource(String sourcePosition) {
        if (board.isBlank(Position.from(sourcePosition))) {
            throw new NoPieceInSourceException();
        }
    }

    /**
     * source와 target이 같은 위치인 경우 예외처리
     * @param sourcePosition
     * @param targetPosition
     */
    private void checkTargetSameAsSource(String sourcePosition, String targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new TargetSameAsSourceException();
        }
    }

    /**
     * source에 있는 기물과 target에 있는 기물의 색이 같은 경우 예외처리
     * @param sourcePosition
     * @param targetPosition
     */
    private void checkIsTargetSameColor(String sourcePosition, String targetPosition) {
        if (board.isSameColor(sourcePosition, targetPosition)) {
            throw new TargetSameColorException();
        }
    }

    /**
     * source-target이 어느 방향인지 확인하고, 올바르지 않은 방향이면 예외처리
     * @param sourcePosition
     * @param targetPosition
     * @return : source-target이 어느 direction인지 반환
     */
    private Piece.Direction getDirection(Position sourcePosition, Position targetPosition) {
        List<Piece.Direction> filteredDirection = board.getDirections(sourcePosition).stream()
                .filter(direction -> targetPosition.isSameDirection(direction.getDegree(), sourcePosition))
                .collect(Collectors.toList());

        if (filteredDirection.isEmpty()) {
            throw new InvalidTargetPositionException();
        }
        return filteredDirection.get(0);
    }

    /**
     * target에 도달 가능하지 않다면 예외처리
     * @param sourcePosition
     * @param targetPosition
     * @param direction
     */
    private void checkTargetReachable(Position sourcePosition, Position targetPosition, Piece.Direction direction) {
        // pawn이 대각선 방향으로 이동할 때 상대편 기물이 존재하지 않는 경우 예외처리
        if (board.isPawn(sourcePosition) && (direction.isNEorNW())) {
            if (board.isBlank(targetPosition)) {
                throw new PawnMoveDiagonalWithNoEnemyException();
            }
        }
        checkReachability(direction.getDegree(), targetPosition, sourcePosition.add(direction.getDegree()), board.getMaxMoveCount(sourcePosition) - 1);
    }

    private void checkReachability(Position direction, Position targetPosition, Position curPosition, int moveCount) {
        if (targetPosition.equals(curPosition)) {
            return;
        }
        if (!board.isBlank(curPosition)) {
            // source - target 경로에 다른 기물이 존재하는 경우 예외 처리
            throw new UnreachableWithObstacleException();
        }
        if (moveCount == 0) {
            // target까지 도달할 수 없을 때 예외처리
            throw new InvalidTargetPositionException();
        }
        checkReachability(direction, targetPosition, curPosition.add(direction), moveCount - 1);
    }

}
