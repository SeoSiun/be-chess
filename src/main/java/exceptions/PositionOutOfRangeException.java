package exceptions;

public class PositionOutOfRangeException extends IllegalArgumentException {
    public PositionOutOfRangeException() {
        super("좌표 범위가 올바르지 않습니다.");
    }
}
