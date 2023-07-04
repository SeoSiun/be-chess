package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;

public class Board {
    private final ArrayList<Pawn> pawns;

    public Board() {
        this.pawns = new ArrayList<>();
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
}
