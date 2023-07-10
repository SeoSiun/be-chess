package chess;

import java.util.Scanner;

public class Chess {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private final Board board;

    public Chess() {
        board = new Board();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String command;

        System.out.println("명령어를 입력해주세요 ('start' / 'end')");

        while (sc.hasNext()) {
            command = sc.nextLine();
            if (command.equals(START)) {
                System.out.println("체스 게임을 시작합니다");
                board.initialize();
                System.out.println(board.showBoard());
            } else if (command.equals(END)) {
                System.out.println("체스 게임을 종료합니다.");
                break;
            } else if (command.startsWith(MOVE)) {
                String[] splited = command.split(" ");

                board.move(splited[1], splited[2]);
                System.out.println(board.showBoard());
            }
        }
    }
}
