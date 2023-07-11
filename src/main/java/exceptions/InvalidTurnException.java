package exceptions;

public class InvalidTurnException extends IllegalArgumentException {
    public InvalidTurnException() {
        super("해당 색상의 차례가 아닙니다.");
    }
}
