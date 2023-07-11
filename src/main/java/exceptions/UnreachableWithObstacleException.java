package exceptions;

public class UnreachableWithObstacleException extends IllegalArgumentException {
    public UnreachableWithObstacleException() {
        super("다른 기물에 의해 막혀있으므로 해당 위치로 이동할 수 없습니다.");
    }
}
