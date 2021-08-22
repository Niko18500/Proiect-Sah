package board;

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
}
