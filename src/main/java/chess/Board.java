package chess;

import chess.pieces.Piece;

import static utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Piece>> board;

    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 8;


    public Board() {
        this.board = new ArrayList<>();
    }

    public void initialize() {
        board.add(createFirstLine(Piece.Color.BLACK));
        board.add(createLineWithOneType(Piece.Color.BLACK, Piece.Type.PAWN));
        for (int i = 0; i < 4; i++) {
            board.add(createLineWithOneType(Piece.Color.NO_COLOR, Piece.Type.NO_PIECE));
        }
        board.add(createLineWithOneType(Piece.Color.WHITE, Piece.Type.PAWN));
        board.add(createFirstLine(Piece.Color.WHITE));
    }

    private List<Piece> createFirstLine(Piece.Color color) {
        return new ArrayList<>() {{
            add(Piece.createPiece(color, Piece.Type.ROOK));
            add(Piece.createPiece(color, Piece.Type.KNIGHT));
            add(Piece.createPiece(color, Piece.Type.BISHOP));
            add(Piece.createPiece(color, Piece.Type.QUEEN));
            add(Piece.createPiece(color, Piece.Type.KING));
            add(Piece.createPiece(color, Piece.Type.BISHOP));
            add(Piece.createPiece(color, Piece.Type.KNIGHT));
            add(Piece.createPiece(color, Piece.Type.ROOK));
        }};
    }

    private List<Piece> createLineWithOneType(Piece.Color color, Piece.Type type) {
        return new ArrayList<>() {{
            for (int i = 0; i < COL_SIZE; i++) {
                add(Piece.createPiece(color, type));
            }
        }};
    }

    public int pieceCount() {
        int count = 0;

        for (List<Piece> line : board) {
            for (Piece piece : line) {
                if (!piece.isNoPiece()) {
                    count++;
                }
            }
        }
        return count;
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        for (List<Piece> line : board) {
            sb.append(appendNewLine(getLineRepresentation(line)));
        }
        return sb.toString();
    }

    private String getLineRepresentation(List<Piece> line) {
        StringBuilder sb = new StringBuilder();

        for (Piece piece : line) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }
}
