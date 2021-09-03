package common;

import board.Pieces;

public class Utilities {
    private Utilities() {
    }

//    public static void set_bit(Pieces pieces, final int square) {
//        pieces.setPositions(pieces.getPositions() | (1L << square));
//    }

//    public static void get_bit(Pieces pieces, final int square) {
//        pieces.getPositions() & (1L << square);
//    }

//    public static void pop_bit(Pieces pieces, final int square) {
//        pieces.setPositions(pieces.getPositions() & (~(1L << square)));
//    }


    public static void printLong(long x) {
        toBinary(x, 64);
    }

    private static void toBinary(long x, int len) {
        if (len > 0) {
            String fullLong;
            fullLong = String.format("%" + len + "s",
                    Long.toBinaryString(x)).replaceAll(" ", "0");
            StringBuilder finalString = new StringBuilder();

            finalString.append(new StringBuilder(fullLong.substring(0, 8)).reverse()).append("\n");
            finalString.append(new StringBuilder(fullLong.substring(8, 16)).reverse()).append("\n");
            finalString.append(new StringBuilder(fullLong.substring(16, 24)).reverse()).append("\n");
            finalString.append(new StringBuilder(fullLong.substring(24, 32)).reverse()).append("\n");
            finalString.append(new StringBuilder(fullLong.substring(32, 40)).reverse()).append("\n");
            finalString.append(new StringBuilder(fullLong.substring(40, 48)).reverse()).append("\n");
            finalString.append(new StringBuilder(fullLong.substring(48, 56)).reverse()).append("\n");
            finalString.append(new StringBuilder(fullLong.substring(56, 64)).reverse()).append("\n\n");

            System.out.println(finalString);
        }
    }

    public static int bitScanForward(long x) {

        if (x == 0) {
            return -1;
        }

        long iter = 1L;

        int counter = 0;
        while ((iter & x) == 0) {
            counter++;
            x = x >> 1;
        }
        return counter;
    }

    public static int bitScanBackward(long x) {

        if (x == 0) {
            return -1;
        }

        long iter = 1L << 63;

        int counter = 63;
        while ((iter & x) == 0) {
            counter--;
            x = x << 1;
        }
        return counter;
    }
}
