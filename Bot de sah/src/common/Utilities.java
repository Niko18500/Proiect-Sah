package common;

import board.Pieces;

public class Utilities {
    private Utilities() {
    }

    public static void set_bit(Pieces pieces, final int square) {
        pieces.setPositions(pieces.getPositions() | (1L << square));
    }

//    public static void get_bit(Pieces pieces, final int square) {
//        pieces.getPositions() & (1L << square);
//    }

    public static void pop_bit(Pieces pieces, final int square) {
        pieces.setPositions(pieces.getPositions() & (~(1L << square)));
    }
}
