package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.BoardTest.BLANK_RANK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

class ChessGameTest {
    private Board board;
    private ChessView chessView;
    private ChessGame chessGame;


    @BeforeEach
    void setup() {
        board = new Board();
        chessView = new ChessView(board);
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("지정한 위치에 기물이 놓여야한다.")
    void move() {
        // given
        board.initializeEmpty();
        String position = "b5";
        Piece piece = PieceFactory.createPiece(Color.BLACK, Piece.Type.ROOK);

        // when
        chessGame.move(position, piece);

        // then
        assertEquals(piece, board.findPiece(position));
        assertEquals(
                appendNewLine(BLANK_RANK + "  8") +
                        appendNewLine(BLANK_RANK + "  7") +
                        appendNewLine(BLANK_RANK + "  6") +
                        appendNewLine(".R......  5") +
                        appendNewLine(BLANK_RANK + "  4") +
                        appendNewLine(BLANK_RANK + "  3") +
                        appendNewLine(BLANK_RANK + "  2") +
                        appendNewLine(BLANK_RANK + "  1") +
                        appendNewLine("") +
                        appendNewLine("abcdefgh"), chessView.showBoard());
    }

    @Test
    @DisplayName("source 위치에 있는 Piece가 target 위치로 옮겨져야 한다.")
    void moveFromSourceToTarget() throws Exception {
        // given
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(PieceFactory.createBlank(), board.findPiece(sourcePosition));
        assertEquals(PieceFactory.createPiece(Color.WHITE, Piece.Type.PAWN), board.findPiece(targetPosition));
    }
}
