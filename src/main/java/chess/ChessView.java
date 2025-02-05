package chess;

import chess.pieces.Piece;

import java.util.stream.IntStream;

import static chess.Board.MAX_RANK;
import static utils.StringUtils.appendNewLine;

/**
 *  체스판 출력 로직
 */
public class ChessView {
    public static final String FILE_LIST = "abcdefgh";
    public static final String INITIAL_MESSAGE = "시작하려면 'start'를 입력하세요!";
    public static final String START_MESSAGE = "체스 게임을 시작합니다!";
    public static final String LINE = "--------------------------------------------------------------";
    public static final String DEFAULT_MESSAGE = "명령어를 입력하세요! (이동: 'move [src] [dst]', 종료: 'end')";
    public static final String END_MESSAGE = "체스 게임을 종료합니다!";
    public static final String BLACK_IS_WIN = "검은색이 승리하였습니다!";
    public static final String WHITE_IS_WIN = "흰색이 승리하였습니다!";
    public static final String KING_IS_GONE = "King을 잡았으므로 ";


    public String showBoard(Board board) {
        StringBuilder sb = new StringBuilder();

        IntStream.range(0, MAX_RANK)
                .forEach(rank -> sb.append(appendNewLine(getRankRepresentation(board, rank))));
        sb.append(appendNewLine(""));
        sb.append(appendNewLine(FILE_LIST));

        return sb.toString();
    }

    private String getRankRepresentation(Board board, int rank) {
        return board.getRankRepresentation(MAX_RANK - rank - 1) + "  " + (MAX_RANK - rank);
    }

    public void printDefaultMessage(Piece.Color turn) {
        System.out.println(LINE);
        System.out.println(appendTurnMessage(turn, DEFAULT_MESSAGE));
    }

    public static String appendTurnMessage(Piece.Color turn, String string) {
        if (turn == Piece.Color.WHITE) {
            return string + " (흰색 차례)";
        }
        return string + " (검은색 차례)";
    }

    public void printPoint(double whitePoint, double blackPoint) {
        System.out.println("   흰색: " + whitePoint + "점, 검은색 " + blackPoint + "점");
    }

    public String getWinnerMessage(double whitePoint, double blackPoint) {
        if (whitePoint < blackPoint) {
            return BLACK_IS_WIN;
        }
        else if (whitePoint > blackPoint) {
            return WHITE_IS_WIN;
        }
        return "무승부입니다!";
    }

    public static String getWinnerMessage(Piece.Color turn) {
        if (turn == Piece.Color.WHITE) {
            return KING_IS_GONE + WHITE_IS_WIN;
        }
        return KING_IS_GONE + BLACK_IS_WIN;
    }
}
