package exceptions;

public class TargetSameColorException extends IllegalArgumentException {
    public TargetSameColorException() {
        super("target에 같은 색의 기물이 위치합니다.");
    }
}
