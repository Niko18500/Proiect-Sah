package board;

import common.Constants;

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
            set_bit(Constants.d1);
        } else {
            set_bit(Constants.d8);
        }
    }

    @Override
    public List<Long> generateMoves(Board board) {
        return null;
    }
}
