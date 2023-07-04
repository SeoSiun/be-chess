package chess;

import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        String command;

        while(sc.hasNext()) {
            command = sc.nextLine();
            if(command.equals("start")) {
                System.out.println("START");
                board.initialize();
                System.out.println(board.print());
            }

            else if(command.equals("end")) {
                System.out.println("END");
                break;
            }
        }
    }
}
