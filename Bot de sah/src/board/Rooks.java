package board;

import common.Constants;

import java.util.List;

public class Rooks extends Pieces {

    public Rooks(final Color color) {
        // Parent constructor
        super();

        // Rooks value
        value = 5;

        // Rooks color
        this.color = color;

        // Initial position
        if (color == Color.WHITE) {
            set_bit(Constants.a1);
            set_bit(Constants.h1);
        } else {
            set_bit(Constants.a8);
            set_bit(Constants.h8);
        }
    }

    @Override
    public List<Long> generateMoves(Board board) {
        return null;
    }
}
