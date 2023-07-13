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

        validateMove(splitCommand);
        chessGame.move(board, splitCommand[1], splitCommand[2]);
        changeTurn();
        System.out.println(chessView.showBoard(board));
    }

    private void validateMove(String[] splitCommand) {
        if (splitCommand.length != 3) {
            throw new IllegalArgumentException("잘못된 인자 개수 입니다.");
        }
        Position sourcePosition = Position.from(splitCommand[1]);

        checkNoPieceInSource(sourcePosition);
        checkValidTurn(sourcePosition);
    }

    /**
     * source에 기물이 없는 경우 예외처리
     */
    private void checkNoPieceInSource(Position sourcePosition) {
        if (board.isBlank(sourcePosition)) {
            throw new NoPieceInSourceException();
        }
    }


    private void checkValidTurn(Position sourcePosition) {
        if (turn == Piece.Color.WHITE && board.isWhite(sourcePosition)) {
            return;
        }
        if (turn == Piece.Color.BLACK && board.isBlack(sourcePosition)) {
            return;
        }
        throw new InvalidTurnException();
    }

    private void changeTurn() {
        if (turn == Piece.Color.WHITE) {
            turn = Piece.Color.BLACK;
        }
        else {
            turn = Piece.Color.WHITE;
        }
    }
}
