package board;

import common.Square;

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
            set_bit(Square.A1.bits);
            set_bit(Square.H1.bits);
        } else {
            set_bit(Square.A8.bits);
            set_bit(Square.H8.bits);
        }
    }

    @Override
    public List<Long> generateMoves(Board board) {
        return null;
    }
}
