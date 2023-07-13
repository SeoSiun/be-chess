package chess;

import chess.pieces.Piece;
import exceptions.InvalidTurnException;
import exceptions.NoPieceInSourceException;

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

    public Game() {
        board = new Board();
        chessGame = new ChessGame();
        chessView = new ChessView();
        turn = Piece.Color.WHITE;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String command;

        System.out.println(INITIAL_MESSAGE);
        while (sc.hasNext()) {
            command = sc.nextLine();
            try {
                if (command.equals(START)) {
                    start();
                } else if (command.equals(END)) {
                    System.out.println(END_MESSAGE);
                    break;
                } else if (command.startsWith(MOVE)) {
                    move(command);
                } else {
                    throw new IllegalArgumentException("존재하지 않는 명령어입니다.");
                }
            } catch (InvalidTurnException exception) {
                System.out.println(chessView.appendTurnMessage(turn, exception.getMessage()));
            } catch (IllegalArgumentException exception) {
                System.out.println(appendNewLine(exception.getMessage()));
            }
            printDefaultMessage();
        }
    }

    private void printDefaultMessage() {
        chessView.printDefaultMessage(turn);

        double whitePoint = chessGame.calculatePoint(board, Piece.Color.WHITE);
        double blackPoint = chessGame.calculatePoint(board, Piece.Color.BLACK);
        chessView.printPoint(whitePoint, blackPoint);
    }

    private void start() {
        board.initialize();
        System.out.println(START_MESSAGE);
        System.out.println(chessView.showBoard(board));
    }

    private void move(String command) {
        String[] splitCommand = command.split(" ");

        if (splitCommand.length != 3) {
            throw new IllegalArgumentException("잘못된 인자 개수 입니다.");
        }
        chessGame.move(board, splitCommand[1], splitCommand[2], turn);
        changeTurn();
        System.out.println(chessView.showBoard(board));
    }

    private void changeTurn() {
        if (turn == Piece.Color.WHITE) {
            turn = Piece.Color.BLACK;
        } else {
            turn = Piece.Color.WHITE;
        }
    }
}
