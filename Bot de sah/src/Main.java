import board.Board;
import board.Color;
import common.Utilities;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Board board = new Board(Color.BLACK); // mai vedem dupa cum se trimite culoarea aia
        List<Long> pos;
        CurrentMove currentMove = new CurrentMove(board);

        XBoard.handshake();

        XBoard.interpretMove(currentMove);

        XBoard.sendMove("d7d5", board);

        pos = board.getBlackKnights().generateMoves(board);
        for (long iter : pos) {
            Utilities.printLong(iter);
        }

        XBoard.interpretMove(currentMove);

        XBoard.sendMove("b8c6", board);

        // Ceva de genul applyMove(currentMove) -> ca sa se aplice miscarea primita de la xBoard (in clasa noastra Board zic)
        // si apoi generateMove(currentMove) -> cu algoritmul (mai e pana acolo :) )

        XBoard.cleanup();
    }
}
