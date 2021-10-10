package board;

import common.Constants;
import common.Square;

import java.util.List;

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
            set_bit(Square.E1.bits);
        } else {
            set_bit(Square.E8.bits);
        }
    }

    @Override
    public List<Long> generateMoves(Board board) {
        return null;
    }
}
