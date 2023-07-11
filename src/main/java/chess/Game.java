package chess;

import exceptions.InvalidTurnException;

import java.util.Scanner;

public class Game {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private final Board board;
    private final ChessGame chessGame;
    private final ChessView chessView;

    private boolean isWhiteTurn;

    public Game() {
        board = new Board();
        chessGame = new ChessGame(board);
        chessView = new ChessView(board);
        isWhiteTurn = true;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String command;

        System.out.println("명령어를 입력해주세요 ('start' / 'end' / 'move [source] [target])");

        while (sc.hasNext()) {
            command = sc.nextLine();
            System.out.println();
            try {
                if (command.equals(START)) {
                    System.out.println("체스 게임을 시작합니다");
                    board.initialize();
                    System.out.println(chessView.showBoard());
                } else if (command.equals(END)) {
                    System.out.println("체스 게임을 종료합니다.");
                    break;
                } else if (command.startsWith(MOVE)) {
                    String[] splitCommand = command.split(" ");

                    chessGame.move(splitCommand[1], splitCommand[2], isWhiteTurn);
                    isWhiteTurn = !isWhiteTurn;
                    System.out.println(chessView.showBoard());
                }
            } catch (InvalidTurnException exception) {
                System.out.println(exception.getMessage() + getTurn());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }

            System.out.println("------------------------------------------------------------");
            System.out.println("명령어를 입력해주세요. ('start' / 'end' / 'move [source] [target])" + getTurn());
        }
    }

    private String getTurn() {
        if (isWhiteTurn) {
            return " (흰색 차례)";
        }
        return " (검은색 차례)";
    }
}
