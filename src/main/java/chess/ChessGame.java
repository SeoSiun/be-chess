package chess;

import chess.pieces.Piece;

import java.util.stream.IntStream;

import static chess.Board.MAX_FILE;
import static chess.Board.MAX_RANK;

/**
 * 체스 규칙에 따른 로직
 */
public class ChessGame {
    // 같은 file에 여러 개의 pawn이 존재하면 0.5점 적용
    private static final double DUPLICATED_PAWN_POINT = 0.5;
    public static final String NO_PIECE_IN_SOURCE = "옮길 기물이 존재하지 않습니다.";
    public static final String INVALID_TURN = "해당 색상의 차례가 아닙니다.";
    public static final String TARGET_EQUALS_SOURCE = "같은 위치로 이동할 수 없습니다.";
    public static final String TARGET_IS_SAME_COLOR = "target에 같은 색의 기물이 위치합니다.";

    public boolean move(Board board, String sourceCoordinate, String targetCoordinate, Piece.Color turn) {
        Position sourcePosition = Position.from(sourceCoordinate);
        Position targetPosition = Position.from(targetCoordinate);

        checkMovable(board, sourcePosition, targetPosition, turn);

        Piece targetPiece = board.findPiece(targetPosition);
        board.move(sourcePosition, targetPosition);

        return targetPiece.isKing();
    }

    /**
     * 이동 유효성 검사
     */
    private void checkMovable(Board board, Position sourcePosition, Position targetPosition, Piece.Color turn) {
        checkNoPieceInSource(board, sourcePosition);
        checkValidTurn(board, sourcePosition, turn);
        checkTargetSameAsSource(sourcePosition, targetPosition);
        checkIsTargetSameColor(board, sourcePosition, targetPosition);
        board.checkMovable(sourcePosition, targetPosition);
    }

    private void checkNoPieceInSource(Board board, Position sourcePosition) {
        if (board.isBlank(sourcePosition)) {
            throw new IllegalArgumentException(NO_PIECE_IN_SOURCE);
        }
    }

    private void checkValidTurn(Board board, Position sourcePosition, Piece.Color turn) {
        if (turn == Piece.Color.WHITE && board.isWhite(sourcePosition)) {
            return;
        }
        if (turn == Piece.Color.BLACK && board.isBlack(sourcePosition)) {
            return;
        }
        throw new IllegalArgumentException(ChessView.appendTurnMessage(turn, INVALID_TURN));
    }

    private void checkTargetSameAsSource(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException(TARGET_EQUALS_SOURCE);
        }
    }

    private void checkIsTargetSameColor(Board board, Position sourcePosition, Position targetPosition) {
        if (board.isSameColor(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException(TARGET_IS_SAME_COLOR);
        }
    }

    /**
     * 점수계산
     */
    public double calculatePoint(Board board, Piece.Color color) {
        double pointExceptPawn = board.getRanks().stream()
                .mapToDouble(rank -> rank.calculatePointExceptPawn(color))
                .sum();

        return pointExceptPawn + calculatePawnPoint(board, color);
    }

    private double calculatePawnPoint(Board board, Piece.Color color) {
        return IntStream.range(0, MAX_FILE)
                .map(file -> countPawnInFile(board, file, color))
                .mapToDouble(ChessGame::getPawnPointByCount).sum();
    }

    private int countPawnInFile(Board board, int file, Piece.Color color) {
        return (int) IntStream.range(0, MAX_RANK)
                .filter(rank -> board.isPawn(file, rank, color)).count();
    }

    private static double getPawnPointByCount(int count) {
        if (count > 1) {
            return DUPLICATED_PAWN_POINT * count;
        }
        return Piece.Type.PAWN.getDefaultPoint() * count;
    }
}
