package chess;

import static chess.Board.MAX_RANK;
import static utils.StringUtils.appendNewLine;

/**
 *  체스판 출력 로직
 */
public class ChessView {
    public String showBoard(Board board) {
        StringBuilder sb = new StringBuilder();

        for (int rank = MAX_RANK; rank > 0; rank--) {
            sb.append(board.getRankRepresentation(rank - 1));
            sb.append(appendNewLine("  " + rank));
        }
        sb.append(appendNewLine(""));
        sb.append(appendNewLine("abcdefgh"));

        return sb.toString();
    }
}
