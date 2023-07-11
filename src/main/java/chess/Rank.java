package chess;

import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {
    private List<Piece> pieces;

    Rank() {
        pieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public Piece getPiece(int index) {
        return pieces.get(index);
    }

    public void setPiece(int index, Piece piece) {
        pieces.set(index, piece);
    }

    public int pieceCount() {
        return (int) pieces.stream()
                .filter(piece -> !piece.isBlank())
                .count();
    }

    public int pieceCount(Piece.Color color, Piece.Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.checkColorAndType(color, type))
                .count();
    }

    public String getRepresentation() {
        StringBuilder sb = new StringBuilder();

        for (Piece piece : pieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public double calculatePointExceptPawn(Piece.Color color) {
        return pieces.stream()
                .filter(piece -> piece.checkColor(color) && !piece.isPawn())
                .mapToDouble(Piece::getPoint).sum();
    }

    public List<Piece> getPiecesByColor(Piece.Color color) {
        return pieces.stream()
                .filter(piece -> piece.checkColor(color))
                .collect(Collectors.toList());
    }
}
