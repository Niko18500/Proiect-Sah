package board;

import common.Square;

import java.util.ArrayList;
import java.util.List;

public class Bishops extends Pieces {

    // Rays


    public Bishops(final Color color) {
        // Parent constructor
        super();

        // Bishops value
        value = 3;

        // Bishops color
        this.color = color;

        // Initial position
        if (color == Color.WHITE) {
            set_bit(Square.valueOfPosition("c1").bits);
            set_bit(Square.valueOfPosition("f1").bits);
        } else {
            set_bit(Square.valueOfPosition("c8").bits);
            set_bit(Square.valueOfPosition("f8").bits);
        }
    }

    // https://www.rhysre.net/fast-chess-move-generation-with-magic-bitboards.html
    @Override
    public List<Long> generateMoves(Board board) {
        // Stores pawns' possible moves
        List<Long> possibleMoves = new ArrayList<>();

        // Generate Rays (4 directii pt fiecare patratel)

        // Get bishop current position
        for (int i = 0; i < 64; i++) {

        }

        return null;
    }
}
