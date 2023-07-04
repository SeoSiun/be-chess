package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;

public class Board {
    private final ArrayList<Pawn> pawns;
    private final ArrayList<ArrayList<Pawn>> board;

    private final int row;
    private final int col;

    private final ArrayList<Pawn> whitePawns;
    private final ArrayList<Pawn> blackPawns;


    public Board() {
        this.pawns = new ArrayList<>();
        this.board = new ArrayList<>();
        this.row = 8;
        this.col = 8;
        this.whitePawns = new ArrayList<>();
        this.blackPawns = new ArrayList<>();
        for(int i = 0; i < col; i++) {
            whitePawns.add(new Pawn(Pawn.WHITE_COLOR));
            blackPawns.add(new Pawn(Pawn.BLACK_COLOR));
        }
    }

    public void add(Pawn pawn) {
        pawns.add(pawn);
    }

    public Pawn findPawn(int index) {
        return pawns.get(index);
    }

    public int size() {
        return pawns.size();
    }

    public void initialize() {
        for(int i = 0; i < row; i++) {
            if(i == 1) board.add(blackPawns);
            else if(i == row - 2) board.add(whitePawns);
            else board.add(new ArrayList<>());
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < row; i++) {
            sb.append(getPawnsResult(board.get(i)));
            if(i != row -1) sb.append("\n");
        }
        return sb.toString();
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(whitePawns);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(blackPawns);
    }

    private String getPawnsResult(ArrayList<Pawn> p) {
        if(p.isEmpty()) return Character.toString(Pawn.EMPTY_REPRESENTATION).repeat(col);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < col; i++) {
            sb.append(p.get(i).getRepresentation());
        }

        return sb.toString();
    }
}
