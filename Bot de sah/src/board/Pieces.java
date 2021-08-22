package board;

public class Pieces {
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

//    public static void get_bit(Pieces pieces, final int square) {
//        pieces.getPositions() & (1L << square);
//    }

    public void pop_bit(final int square) {
        positions = positions & (~(1L << square));
    }
}
