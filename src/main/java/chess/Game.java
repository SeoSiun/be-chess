package chess;

import chess.pieces.Piece;

import java.util.Scanner;

import static chess.ChessView.*;
import static utils.StringUtils.appendNewLine;

public class Game {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private final Board board;
    private final ChessGame chessGame;
    private final ChessView chessView;
    private Piece.Color turn;

    private boolean isKing;
    private boolean isStarted;

    public Game() {
        board = new Board();
        chessGame = new ChessGame();
        chessView = new ChessView();
        turn = Piece.Color.WHITE;
        isKing = false;
        isStarted = false;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String command;

        System.out.println(INITIAL_MESSAGE);

        while (!isKing && sc.hasNext()) {
            command = sc.nextLine();
            try {
                if (command.equals(START)) {
                    start();
                } else if (command.equals(END)) {
                    isStarted();
                    break;
                } else if (command.startsWith(MOVE)) {
                    isStarted();
                    move(command);
                } else {
                    throw new IllegalArgumentException("존재하지 않는 명령어입니다.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(appendNewLine(exception.getMessage()));
            }
        }
        end();
    }

    private void printDefaultMessage() {
        chessView.printDefaultMessage(turn);

        double whitePoint = chessGame.calculatePoint(board, Piece.Color.WHITE);
        double blackPoint = chessGame.calculatePoint(board, Piece.Color.BLACK);
        chessView.printPoint(whitePoint, blackPoint);
    }

    private void start() {
        isStarted = true;
        board.initialize();
        System.out.println(START_MESSAGE);
        System.out.println(chessView.showBoard(board));
        printDefaultMessage();
    }

    private void isStarted() {
        if (!isStarted) {
            throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다!");
        }
    }

    private void move(String command) {
        String[] splitCommand = command.split(" ");
        checkArgumentLength(splitCommand.length);
        isKing = chessGame.move(board, splitCommand[1], splitCommand[2], turn);
        if (!isKing) {
            continueGame();
        }
    }

    private void continueGame() {
        changeTurn();
        System.out.println(chessView.showBoard(board));
        printDefaultMessage();
    }

    private void checkArgumentLength(int length) {
        if (length != 3) {
            throw new IllegalArgumentException("잘못된 인자 개수 입니다.");
        }
    }

    private void end() {
        double whitePoint = chessGame.calculatePoint(board, Piece.Color.WHITE);
        double blackPoint = chessGame.calculatePoint(board, Piece.Color.BLACK);

        System.out.println(END_MESSAGE);
        chessView.printPoint(whitePoint, blackPoint);
        System.out.println(LINE);

        if (isKing) {
            System.out.println(getWinnerMessage(turn));
        }
        else {
            System.out.println(chessView.getWinnerMessage(whitePoint, blackPoint));
        }
    }

    private void changeTurn() {
        if (turn == Piece.Color.WHITE) {
            turn = Piece.Color.BLACK;
        } else {
            turn = Piece.Color.WHITE;
        }
    }
}
