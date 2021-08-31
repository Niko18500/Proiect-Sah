package board;

import common.Constants;
import common.Square;

import java.util.ArrayList;
import java.util.List;

public class Knights extends Pieces {

    // Contains all knight attacks for each square
    private List<Long> attacks;

    public Knights(final Color color) {
        // Parent constructor
        super();

        // Knight value
        value = 3;

        // Knight color
        this.color = color;

        // Initial position
        if (color == Color.WHITE) {
            set_bit(Square.valueOfPosition("b1").bits);
            set_bit(Square.valueOfPosition("g1").bits);
        } else {
            set_bit(Square.valueOfPosition("b8").bits);
            set_bit(Square.valueOfPosition("g8").bits);
        }

        // Generate knight attacks
        generateAttacks();
    }

    private void generateAttacks() {
        attacks = new ArrayList<>();

        long bitboard;
        long currentAttack;

        for (int i = 0; i < 64; i++) {
            currentAttack = 0;
            bitboard = set_bit(0, i);

            if (((bitboard << 17) & Constants.notAFile) != 0) currentAttack |= (bitboard << 17);
            if (((bitboard << 15) & Constants.notHFile) != 0) currentAttack |= (bitboard << 15);
            if (((bitboard << 10) & Constants.notABFile) != 0) currentAttack |= (bitboard << 10);
            if (((bitboard << 6) & Constants.notGHFile) != 0) {currentAttack |= (bitboard << 6);}

            if (((bitboard >> 17) & Constants.notHFile) != 0) currentAttack |= (bitboard >> 17);
            if (((bitboard >> 15) & Constants.notAFile) != 0) currentAttack |= (bitboard >> 15);
            if (((bitboard >> 10) & Constants.notGHFile) != 0) currentAttack |= (bitboard >> 10);
            if (((bitboard >> 6) & Constants.notABFile) != 0) currentAttack |= (bitboard >> 6);

            attacks.add(currentAttack);
        }
    }

    // https://www.youtube.com/watch?v=Pq98mQTTwWY
    // https://github.com/maksimKorzh/chess_programming/blob/master/src/bitboards/knight_attacks/bitboard.c
    @Override
    public List<Long> generateMoves(Board board) {
        // Stores pawns' possible moves
        List<Long> possibleMoves = new ArrayList<>();

        // Iterate through positions (our colored knights on the board)
        for (int i = 0; i < 64; i++) {
            long iter = 1L << i;

            // Check if we have a possible knight move
            if ((iter & positions) != 0) {
                long attack = attacks.get(i);

                // Iterate through attacks mask bits
                for (int j = 0; j < 64; j++) {
                    long iterJ = 1L << j;

                    if ((iterJ & attack) != 0) {
                        // Check if my pieces are not there
                        if ((iterJ & attack & board.getMyPositions()) == 0) {
                            // Add to possible moves list
                            possibleMoves.add(iterJ);
                        }
                    }
                }
            }
        }

        return possibleMoves;
    }

}
