import board.Board;
import common.Square;

import java.io.*;

public class XBoard {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private XBoard() {
    }

    public static void handshake() throws IOException {
        // Xboard
        reader.readLine();

        // Proto
        reader.readLine();

        System.out.println("feature sigint=0 san=0 name=SuperBot done=1\n");

        // 4 x accept feature
        for (int i = 0; i < 4; i++) {
            reader.readLine();
        }

        // New
        reader.readLine();

        // Random + level 40 5 0 + post + hard
        for (int i = 0; i < 4; i++) {
            reader.readLine();
        }
    }

    public static void interpretMove(CurrentMove currentMove) throws IOException {
        currentMove.setTime(reader.readLine());
        currentMove.setOtim(reader.readLine());
        currentMove.setMove(reader.readLine());
        currentMove.registerMove();
    }

    public static void sendMove(final String move, Board board) {
        System.out.println("move " + move + "\n");

        // Register move for us (temporary -> until we choose our own move)

        String oldMove = move.substring(0, 2);
        String newMove = move.substring(2, 4);

        long moveToApply =
                (1L << Square.valueOfPosition(oldMove).bits) | (1L << Square.valueOfPosition(newMove).bits);
        // The piece that moved
        long oldMoveBit = 1L << Square.valueOfPosition(oldMove).bits;

        // Update all positions bitboard
        board.setAllPositions(moveToApply ^ board.getAllPositions());

        // Identify which piece moved

        // If it was a white piece
        if ((oldMoveBit & board.getWhitePositions()) != 0L) {
            // Update white positions bitboard
            board.setWhitePositions(moveToApply ^ board.getWhitePositions());

            // If it is a pawn move
            if ((oldMoveBit & board.getWhitePawns().getPositions()) != 0L) {
                // Update pawn bitboard
                board.getWhitePawns()
                        .setPositions(moveToApply ^ board.getWhitePawns().getPositions());
                // If it is a rook move
            } else if ((oldMoveBit & board.getWhiteRooks().getPositions()) != 0L) {
                // Update rook bitboard
                board.getWhiteRooks()
                        .setPositions(moveToApply ^ board.getWhiteRooks().getPositions());
                // If it is a knight move
            } else if ((oldMoveBit & board.getWhiteKnights().getPositions()) != 0L) {
                // Update knight bitboard
                board.getWhiteKnights()
                        .setPositions(moveToApply ^ board.getWhiteKnights().getPositions());
                // If it is a bishop move
            } else if ((oldMoveBit & board.getWhiteBishops().getPositions()) != 0L) {
                // Update bishop bitboard
                board.getWhiteBishops()
                        .setPositions(moveToApply ^ board.getWhiteBishops().getPositions());
                // If it is a queen move
            } else if ((oldMoveBit & board.getWhiteQueen().getPositions()) != 0L) {
                // Update queen bitboard
                board.getWhiteQueen()
                        .setPositions(moveToApply ^ board.getWhiteQueen().getPositions());
                // If it is a king move
            } else if ((oldMoveBit & board.getWhiteKing().getPositions()) != 0L) {
                // Update king bitboard
                board.getWhiteKing()
                        .setPositions(moveToApply ^ board.getWhiteKing().getPositions());
            }

            // If it was a black piece
        } else if ((oldMoveBit & board.getBlackPositions()) != 0L) {
            // Update black pieces bitboard
            board.setBlackPositions(moveToApply ^ board.getBlackPositions());

            // If it is a pawn move
            if ((oldMoveBit & board.getBlackPawns().getPositions()) != 0L) {
                // Update pawn bitboard
                board.getBlackPawns()
                        .setPositions(moveToApply ^ board.getBlackPawns().getPositions());
                // If it is a rook move
            } else if ((oldMoveBit & board.getBlackRooks().getPositions()) != 0L) {
                // Update rook bitboard
                board.getBlackRooks()
                        .setPositions(moveToApply ^ board.getBlackRooks().getPositions());
                // If it is a knight move
            } else if ((oldMoveBit & board.getBlackKnights().getPositions()) != 0L) {
                // Update knight bitboard
                board.getBlackKnights()
                        .setPositions(moveToApply ^ board.getBlackKnights().getPositions());
                // If it is a bishop move
            } else if ((oldMoveBit & board.getBlackBishops().getPositions()) != 0L) {
                // Update bishop bitboard
                board.getBlackBishops()
                        .setPositions(moveToApply ^ board.getBlackBishops().getPositions());
                // If it is a queen move
            } else if ((oldMoveBit & board.getBlackQueen().getPositions()) != 0L) {
                // Update queen bitboard
                board.getBlackQueen()
                        .setPositions(moveToApply ^ board.getBlackQueen().getPositions());
                // If it is a king move
            } else if ((oldMoveBit & board.getBlackKing().getPositions()) != 0L) {
                // Update king bitboard
                board.getBlackKing()
                        .setPositions(moveToApply ^ board.getBlackKing().getPositions());
            }
        }
    }

    public static void cleanup() throws IOException {
        reader.close();
    }
}
