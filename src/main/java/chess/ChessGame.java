package chess;

import chess.pieces.Piece;
import chess.pieces.PieceFactory;

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
     * @param coordinate : 기물을 놓을 위치
     * @param piece : 놓을 기물
     */
    public void move(String coordinate, Piece piece) {
        board.move(Position.from(coordinate), piece);
    }

    /**
     * source에 있는 기물을 target으로 옮김
     * @param sourcePosition : 옮기기 전 위치
     * @param targetPosition : 옮길 위치
     */
    public void move(String sourcePosition, String targetPosition) {
        Piece pieceToMove = board.findPiece(sourcePosition);

        move(sourcePosition, PieceFactory.createBlank());
        move(targetPosition, pieceToMove);
    }
}
