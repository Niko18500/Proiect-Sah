import board.Board;
import board.Color;
import common.Utilities;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        XBoard.handshake();

        Board board = new Board(Color.BLACK); // mai vedem dupa cum se trimite culoarea aia
        List<Long> pos;
        CurrentMove currentMove = new CurrentMove(board);

//        Utilities.printLong(board.getBlackBishops().getRays().get("SW").get(Square.D4.bits));

        XBoard.interpretMove(currentMove);

        XBoard.sendMove("d7d5", board);



        XBoard.interpretMove(currentMove);

        XBoard.sendMove("c8f5", board);

        XBoard.interpretMove(currentMove);

        XBoard.sendMove("e7e5", board);

        XBoard.interpretMove(currentMove);

        XBoard.sendMove("f8c5", board);

        pos = board.getBlackBishops().generateMoves(board);
        for (long iter : pos) {
            Utilities.printLong(iter);
        }

        // Ceva de genul applyMove(currentMove) -> ca sa se aplice miscarea primita de la xBoard (in clasa noastra Board zic)
        // si apoi generateMove(currentMove) -> cu algoritmul (mai e pana acolo :) )

        XBoard.cleanup();
    }
}
