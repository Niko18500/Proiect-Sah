import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        CurrentMove currentMove = new CurrentMove();

        XBoard.handshake();

        XBoard.interpretMove(currentMove);
        
        XBoard.sendMove("d7d5");

        XBoard.interpretMove(currentMove);

        XBoard.sendMove("b8c6");

        // Ceva de genul applyMove(currentMove) -> ca sa se aplice miscarea primita de la xBoard (in clasa noastra Board zic)
        // si apoi generateMove(currentMove) -> cu algoritmul (mai e pana acolo :) )

        XBoard.cleanup();
    }
}
