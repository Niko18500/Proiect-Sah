package board;

public class Board {

    // Color of the bot (temporary here -> in Board)
    private Color botColor;

    // All pieces on the board (| of all positions)
    private long allPositions;

    // All white pieces on the board
    private long whitePositions;
    // All black pieces on the board
    private long blackPositions;

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

    public Board(final Color botColor) {
        // Bot color
        this.botColor = botColor;

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

        // All white pieces on the board
        whitePositions = whitePawns.positions | whiteRooks.positions | whiteKnights.positions |
                whiteBishops.positions | whiteQueen.positions | whiteKing.positions;

        // All black pieces on the board
        blackPositions = blackPawns.positions | blackRooks.positions | blackKnights.positions |
                blackBishops.positions | blackQueen.positions | blackKing.positions;

        // The complete board (all pieces)
        allPositions = whitePositions | blackPositions;
    }

    public long getOpponentPositions() {
        if (botColor == Color.WHITE) {
            return blackPositions;
        } else {
            return whitePositions;
        }
    }

    public Color getBotColor() {
        return botColor;
    }

    public void setBotColor(Color botColor) {
        this.botColor = botColor;
    }

    public long getAllPositions() {
        return allPositions;
    }

    public void setAllPositions(long allPositions) {
        this.allPositions = allPositions;
    }

    public long getWhitePositions() {
        return whitePositions;
    }

    public void setWhitePositions(long whitePositions) {
        this.whitePositions = whitePositions;
    }

    public long getBlackPositions() {
        return blackPositions;
    }

    public void setBlackPositions(long blackPositions) {
        this.blackPositions = blackPositions;
    }

    public Pawns getWhitePawns() {
        return whitePawns;
    }

    public void setWhitePawns(Pawns whitePawns) {
        this.whitePawns = whitePawns;
    }

    public Pawns getBlackPawns() {
        return blackPawns;
    }

    public void setBlackPawns(Pawns blackPawns) {
        this.blackPawns = blackPawns;
    }
}
