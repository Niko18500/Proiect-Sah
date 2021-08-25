package board;

import common.Constants;

import java.util.List;

public class Knights extends Pieces {

    public Knights(final Color color) {
        // Parent constructor
        super();

        // Knight value
        value = 3;

        // Knight color
        this.color = color;

        // Initial position
        if (color == Color.WHITE) {
            set_bit(Constants.b1);
            set_bit(Constants.g1);
        } else {
            set_bit(Constants.b8);
            set_bit(Constants.g8);
        }
    }

    @Override
    public List<Long> generateMoves(Board board) {
        return null;
    }
}
