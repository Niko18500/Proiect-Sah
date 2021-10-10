package board;

import common.Square;

import java.util.List;

public class Queen extends Pieces {

    public Queen(final Color color) {
        // Parent constructor
        super();

        // Queen value
        value = 9;

        // Queen color
        this.color = color;

        // Initial position
        if (color == Color.WHITE) {
            set_bit(Square.D1.bits);
        } else {
            set_bit(Square.D8.bits);
        }
    }

    @Override
    public List<Long> generateMoves(Board board) {
        return null;
    }
}
