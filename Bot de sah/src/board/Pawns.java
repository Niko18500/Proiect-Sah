package board;

import java.util.ArrayList;
import java.util.List;

public class Pawns extends Pieces {

    public Pawns(final Color color) {
        // Parent constructor
        super();

        // Pawn value
        value = 1;

        // Pawn color
        this.color = color;

        // Initial position
        if (color == Color.WHITE) {
            positions = 255L << 8;
        } else {
            positions = 255L << (8 * 6);
        }
    }

    @Override
    public List<Long> generateMoves(Board board) {
        // Stores pawns' possible moves
        List<Long> possibleMoves = new ArrayList<>();

        // Multiplier (move direction by color)
        int sign;
        if (color == Color.WHITE) {
            // +1 for white
            sign = 1;
        } else {
            // -1 for black
            sign = -1;
        }

        // Ignore 1st and 8th ranks
        for (int i = 8; i <= 56; i++) {
            // Check if this position has a pawn (of our color)
            if (super.get_bit(positions, i)) {
                // If 2nd rank (for white) or 7th rank (for black), it can move 2 squares
                if (((i <= 16) && (color == Color.WHITE)) ||
                        ((i >= 48) && (color == Color.BLACK))) {
                    // Check if move is possible (no other piece exists there already)
                    if (!super.get_bit(board.getAllPositions(), i + 16 * sign)) {
                        // Move pawn
                        Long move = super.set_bit(super.pop_bit(positions, i), i + 16 * sign);
                        // Add pawn move to possibleMoves list
                        possibleMoves.add(move);
                    }
                }
                // 1 square move
                if (!super.get_bit(board.getAllPositions(), i + 8 * sign)) {
                    // Move pawn
                    Long move = super.set_bit(super.pop_bit(positions, i), i + 8 * sign);
                    // Add pawn move to possibleMoves list
                    possibleMoves.add(move);
                }

                // Check for captures
                // If 1st or 8th file, ignore "out of board" captures
                if (super.get_bit(board.getOpponentPositions(), i + 7 * sign)) {
                    if ((i % 8 != 0) && (color == Color.WHITE) ||
                            (i % 8 != 7) && (color == Color.BLACK)) {
                        // Capture left piece
                        Long move = super.set_bit(super.pop_bit(positions, i), i + 7 * sign);
                        // Add pawn move to possibleMoves list
                        possibleMoves.add(move);
                    }
                }
                if (super.get_bit(board.getOpponentPositions(), i + 9 * sign)) {
                    if ((i % 8 != 0) && (color == Color.BLACK) ||
                            (i % 8 != 7) && (color == Color.WHITE)) {
                        // Capture right piece
                        Long move = super.set_bit(super.pop_bit(positions, i), i + 9 * sign);
                        // Add pawn move to possibleMoves list
                        possibleMoves.add(move);
                    }
                }
            }
        }

        return possibleMoves;
    }
}
