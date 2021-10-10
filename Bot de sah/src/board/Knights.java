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
            set_bit(Square.B1.bits);
            set_bit(Square.G1.bits);
        } else {
            set_bit(Square.B8.bits);
            set_bit(Square.G8.bits);
        }

        // Generate knight attacks
        generateAttacks();
    }

    /**
     *     Generates all knight attacks (for each square)
     */
    private void generateAttacks() {
        attacks = new ArrayList<>();

        // Square we generate attacks for
        long currentSquare;

        // All attacks possible from current square
        long currentAttack;

        // For each square on the board
        for (int i = 0; i < 64; i++) {
            currentAttack = 0;
            currentSquare = set_bit(0, i);

            // Ignore "out of board" moves and generate current attack
            if (((currentSquare << 17) & Constants.notAFile) != 0) currentAttack |= (currentSquare << 17);
            if (((currentSquare << 15) & Constants.notHFile) != 0) currentAttack |= (currentSquare << 15);
            if (((currentSquare << 10) & Constants.notABFile) != 0) currentAttack |= (currentSquare << 10);
            if (((currentSquare << 6) & Constants.notGHFile) != 0) currentAttack |= (currentSquare << 6);

            if (((currentSquare >> 17) & Constants.notHFile) != 0) currentAttack |= (currentSquare >> 17);
            if (((currentSquare >> 15) & Constants.notAFile) != 0) currentAttack |= (currentSquare >> 15);
            if (((currentSquare >> 10) & Constants.notGHFile) != 0) currentAttack |= (currentSquare >> 10);
            if (((currentSquare >> 6) & Constants.notABFile) != 0) currentAttack |= (currentSquare >> 6);

            // Add current attack to attack list (in order of squares)
            attacks.add(currentAttack);
        }
    }

    // https://www.youtube.com/watch?v=Pq98mQTTwWY
    // https://github.com/maksimKorzh/chess_programming/blob/master/src/bitboards/knight_attacks/bitboard.c
    @Override
    public List<Long> generateMoves(Board board) {
        // Stores knights' possible moves
        List<Long> possibleMoves = new ArrayList<>();

        // Iterate through positions (our colored knights on the board)
        for (int i = 0; i < 64; i++) {
            long iter = 1L << i;

            // Check if we have a knight there
            if ((iter & positions) != 0) {
                // Get attacks for that square
                long attack = attacks.get(i);

                // Iterate through attacks mask bits
                for (int j = 0; j < 64; j++) {
                    long iterJ = 1L << j;

                    // Check if we have an attack for that square
                    if ((iterJ & attack) != 0) {
                        // Check that none of my pieces are there
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
