package common;

import java.util.HashMap;
import java.util.Map;

public enum Square {
    A1("a1", 0),
    B1("b1", 1),
    C1("c1", 2),
    D1("d1", 3),
    E1("e1", 4),
    F1("f1", 5),
    G1("g1", 6),
    H1("h1", 7),

    A2("a2", 8),
    B2("b2", 9),
    C2("c2", 10),
    D2("d2", 11),
    E2("e2", 12),
    F2("f2", 13),
    G2("g2", 14),
    H2("h2", 15),

    A3("a3", 16),
    B3("b3", 17),
    C3("c3", 18),
    D3("d3", 19),
    E3("e3", 20),
    F3("f3", 21),
    G3("g3", 22),
    H3("h3", 23),

    A4("a4", 24),
    B4("b4", 25),
    C4("c4", 26),
    D4("d4", 27),
    E4("e4", 28),
    F4("f4", 29),
    G4("g4", 30),
    H4("h4", 31),

    A5("a5", 32),
    B5("b5", 33),
    C5("c5", 34),
    D5("d5", 35),
    E5("e5", 36),
    F5("f5", 37),
    G5("g5", 38),
    H5("h5", 39),

    A6("a6", 40),
    B6("b6", 41),
    C6("c6", 42),
    D6("d6", 43),
    E6("e6", 44),
    F6("f6", 45),
    G6("g6", 46),
    H6("h6", 47),

    A7("a7", 48),
    B7("b7", 49),
    C7("c7", 50),
    D7("d7", 51),
    E7("e7", 52),
    F7("f7", 53),
    G7("g7", 54),
    H7("h7", 55),

    A8("a8", 56),
    B8("b8", 57),
    C8("c8", 58),
    D8("d8", 59),
    E8("e8", 60),
    F8("f8", 61),
    G8("g8", 62),
    H8("h8", 63);

    private static final Map<String, Square> BY_POSITION = new HashMap<>();
    private static final Map<Integer, Square> BY_BITS = new HashMap<>();

    static {
        for (Square e : values()) {
            BY_POSITION.put(e.position, e);
            BY_BITS.put(e.bits, e);
        }
    }

    public final String position;
    public final int bits;

    private Square(String position, int bits) {
        this.position = position;
        this.bits = bits;
    }

    public static Square valueOfPosition(String position) {
        return BY_POSITION.get(position);
    }

    public static Square valueOfBits(int bits) {
        return BY_BITS.get(bits);
    }
}