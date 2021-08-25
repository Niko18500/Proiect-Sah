package board;

import java.util.List;

public abstract class Pieces {
    long positions;
    int value;
    Color color;

    public long getPositions() {
        return positions;
    }

    public void setPositions(long positions) {
        this.positions = positions;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void set_bit(final int square) {
        positions = positions | (1L << square);
    }

    public long set_bit(long positions, final int square) {
        return positions | (1L << square);
    }

    public boolean get_bit(final long positions, final int square) {
        return Long.bitCount(positions & (1L << square)) == 1;
    }

    public void pop_bit(final int square) {
        positions = positions & (~(1L << square));
    }

    public long pop_bit(long positions, final int square) {
        return positions & (~(1L << square));
    }

    public abstract List<Long> generateMoves(Board board);

}
