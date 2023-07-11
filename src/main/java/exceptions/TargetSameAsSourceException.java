package exceptions;

public class TargetSameAsSourceException extends IllegalArgumentException {
    public TargetSameAsSourceException() {
        super("source position과 target position이 같을 수 없습니다.");
    }
}
