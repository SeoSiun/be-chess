package exceptions;

public class NoPieceInSourceException extends IllegalArgumentException {
    public NoPieceInSourceException() {
        super("옮길 기물이 존재하지 않습니다.");
    }
}
