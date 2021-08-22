package board;

public class Board {

    // Pawns
    private Pawns whitePawns;
    private Pawns blackPawns;

    // Rooks
    private Rooks whiteRooks;
    private Rooks blackRooks;

    // Knights
    private Knights whiteKnights;
    private Knights blackKnights;

    // Bishops
    private Bishops whiteBishops;
    private Bishops blackBishops;

    // Queen
    private Queen whiteQueen;
    private Queen blackQueen;

    // King
    private King whiteKing;
    private King blackKing;

    public Board() {
        // Pawns
        whitePawns = new Pawns(Color.WHITE);
        blackPawns = new Pawns(Color.BLACK);

        // Rooks
        whiteRooks = new Rooks(Color.WHITE);
        blackRooks = new Rooks(Color.BLACK);

        // Knights
        whiteKnights = new Knights(Color.WHITE);
        blackKnights = new Knights(Color.BLACK);

        // Bishops
        whiteBishops = new Bishops(Color.WHITE);
        blackBishops = new Bishops(Color.BLACK);

        // Queen
        whiteQueen = new Queen(Color.WHITE);
        blackQueen = new Queen(Color.BLACK);

        // King
        whiteKing = new King(Color.WHITE);
        blackKing = new King(Color.BLACK);
    }
}
