package exceptions;

public class PawnMoveDiagonalWithNoEnemyException extends IllegalArgumentException {
    public PawnMoveDiagonalWithNoEnemyException() {
        super("Pawn은 상대 편 기물이 있을 때만 대각선으로 움직일 수 있습니다.");
    }
}
