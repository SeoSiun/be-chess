package exceptions;

public class InvalidDirectionException extends IllegalArgumentException {
    public InvalidDirectionException() {
        super("해당 기물이 이동할 수 없는 방향입니다.");
    }
}
