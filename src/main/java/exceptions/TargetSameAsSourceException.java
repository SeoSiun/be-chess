package exceptions;

public class TargetSameAsSourceException extends IllegalArgumentException {
    public TargetSameAsSourceException() {
        super("같은 위치로 이동할 수 없습니다.");
    }
}
