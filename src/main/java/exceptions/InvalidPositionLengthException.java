package exceptions;

public class InvalidPositionLengthException extends IllegalArgumentException {
    public InvalidPositionLengthException() {
        super("좌표 길이는 2여야 합니다.([file][rank])");
    }
}
