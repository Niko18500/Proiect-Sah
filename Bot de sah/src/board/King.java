package board;

import common.Constants;

public class King extends Pieces {

    public King(final Color color) {
        // Parent constructor
        super();

        // King value
        value = Integer.MAX_VALUE;

        // King color
        this.color = color;

        // Initial position
        if (color == Color.WHITE) {
            set_bit(Constants.e1);
        } else {
            set_bit(Constants.e8);
        }
    }
}
