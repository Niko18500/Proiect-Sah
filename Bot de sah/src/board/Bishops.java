package board;

import common.Constants;

public class Bishops extends Pieces {

    public Bishops(final Color color) {
        // Parent constructor
        super();

        // Bishops value
        value = 3;

        // Bishops color
        this.color = color;

        // Initial position
        if (color == Color.WHITE) {
            set_bit(Constants.c1);
            set_bit(Constants.f1);
        } else {
            set_bit(Constants.c8);
            set_bit(Constants.f8);
        }
    }
}
