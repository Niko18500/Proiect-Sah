import board.Board;
import board.Color;
import common.Square;
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

//        System.out.println(Utilities.bitScanForward(2L));

        System.out.println(Utilities.bitScanBackward(3));

        XBoard.interpretMove(currentMove);

        XBoard.sendMove("d7d5", board);



        XBoard.interpretMove(currentMove);

        pos = board.getBlackBishops().generateMoves(board);
        for (long iter : pos) {
            Utilities.printLong(iter);
        }

        XBoard.sendMove("b8c6", board);

        // Ceva de genul applyMove(currentMove) -> ca sa se aplice miscarea primita de la xBoard (in clasa noastra Board zic)
        // si apoi generateMove(currentMove) -> cu algoritmul (mai e pana acolo :) )

        XBoard.cleanup();
    }
}
