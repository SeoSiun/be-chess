package exceptions;

public class InvalidTargetPositionException extends IllegalArgumentException {
    public InvalidTargetPositionException() {
        super("해당 기물이 이동할 수 없는 위치입니다.");
    }
}
