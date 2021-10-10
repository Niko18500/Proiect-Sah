package board;

import common.Square;
import common.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bishops extends Pieces {

    // Rays <direction, rays for each square>
    private Map<String, List<Long>> rays;

    // Attacks (2 attacks, one for each bishop)
    private List<Long> attacks;

    // Poate pt rooks sau queen
//    public Map<String, List<Long>> getRays() {
//        return rays;
//    }

    public Bishops(final Color color) {
        // Parent constructor
        super();

        // Bishops value
        value = 3;

        // Bishops color
        this.color = color;

        // Initial position
        if (color == Color.WHITE) {
            set_bit(Square.C1.bits);
            set_bit(Square.F1.bits);
        } else {
            set_bit(Square.C8.bits);
            set_bit(Square.F8.bits);
        }

        // Generate rays
        generateRays();
    }

    /**
     * Generates diagonal rays for each square for each of the 4 directions
     */
    private void generateRays() {
        // Store rays
        rays = new HashMap<>();

        List<Long> raysNE = new ArrayList<>();
        List<Long> raysNW = new ArrayList<>();
        List<Long> raysSE = new ArrayList<>();
        List<Long> raysSW = new ArrayList<>();

        // Generate rays for each square
        int rank, file;

        for (int square = 0; square < 64; square++) {

            int rankTarget = square / 8;
            int fileTarget = square % 8;

            // North-East rays
            long currentAttacks = 0;
            for (rank = rankTarget + 1, file = fileTarget + 1; rank <= 7 && file <= 7; rank++, file++) {
                currentAttacks |= (1L << (rank * 8 + file));
            }
            raysNE.add(currentAttacks);

            // North-West rays
            currentAttacks = 0;
            for (rank = rankTarget + 1, file = fileTarget - 1; rank <= 7 && file >= 0; rank++, file--) {
                currentAttacks |= (1L << (rank * 8 + file));
            }
            raysNW.add(currentAttacks);

            // South-East rays
            currentAttacks = 0;
            for (rank = rankTarget - 1, file = fileTarget + 1; rank >= 0 && file <= 7; rank--, file++) {
                currentAttacks |= (1L << (rank * 8 + file));
            }
            raysSE.add(currentAttacks);

            // South-West rays
            currentAttacks = 0;
            for (rank = rankTarget - 1, file = fileTarget - 1; rank >= 0 && file >= 0; rank--, file--) {
                currentAttacks |= (1L << (rank * 8 + file));
            }
            raysSW.add(currentAttacks);
        }

        rays.put("NE", raysNE);
        rays.put("NW", raysNW);
        rays.put("SE", raysSE);
        rays.put("SW", raysSW);
    }

    /**
     * Generates the 2 bishop attacks for the current state of the board
     * https://www.rhysre.net/fast-chess-move-generation-with-magic-bitboards.html
     */
    private void generateAttacks(Board board) {
        // Idee: o functie care extrage fiecare bit de 1 din attacks (pt ca ne trb fiecare mutare separata)
        // poate la rooks s-o standardizam cumva -> vedem


        // Stores bishops' possible attacks
        attacks = new ArrayList<>();

        // Bishops' current positions (we only have 2 set bits in "positions")
        List<Integer> bishopSquares = Arrays.asList(Utilities.bitScanLSB(positions),
                                                    Utilities.bitScanMSB(positions));

        // For each of the 2 bishops
        for (int bishopSquare : bishopSquares) {

            // Stores all bishop attacks for the current square
            long allAttacksRay = 0;

            // All other pieces on the board except current bishop
            long blockers = pop_bit(board.getAllPositions(), bishopSquare);

            // The 4 directions
            List<String> directions = Arrays.asList("NW", "NE", "SW", "SE");

            // For each direction
            for (String direction : directions) {

                // Stores all bishop attacks for the current square and direction
                long attacksRay = 0;

                // Stores blocking pieces for the current ray
                long maskedBlockers = blockers &
                                        rays.get(direction).get(bishopSquare);

                // Stores the first blocking square number in the current ray
                int firstBlockingSquare;
                // Check if North or South (if N -> LSB, if S -> MSB)
                if (direction.equals("NW") || direction.equals("NE")) {
                    firstBlockingSquare = Utilities.bitScanLSB(maskedBlockers);
                } else {
                    firstBlockingSquare = Utilities.bitScanMSB(maskedBlockers);
                }

                // If we have a blocking square, we have a piece in sight
                if (firstBlockingSquare != -1) {
                    // Has 1 set bit on the first blocking square number
                    long firstBlockingPiece = 1L << firstBlockingSquare;

                    // Generate attack ray for current direction stopping at
                    // the first blocking piece (including it)
                    attacksRay = rays.get(direction).get(bishopSquare) &
                            ~rays.get(direction).get(firstBlockingSquare);

                    // Check if it's my piece
                    if ((firstBlockingPiece & board.getMyPositions()) != 0) {
                        // Remove it from attack (we can't capture it)
                        attacksRay &= ~firstBlockingPiece;
                    }
                    // Else it's the opponent's piece (we can capture it)

                // No pieces in sight
                } else {
                    // The attacks ray is the ray itself
                    attacksRay = rays.get(direction).get(bishopSquare);
                }

                // Save attacks ray (for current direction)
                allAttacksRay |= attacksRay;
            }

            // Save all attacks for the current bishop
            attacks.add(allAttacksRay);
        }
    }

    @Override
    public List<Long> generateMoves(Board board) {
        // Generates
        generateAttacks(board);

        // Stores bishops' possible moves
        List<Long> possibleMoves = new ArrayList<>();

        // For the 2 attacks (one per bishop)
        for (long attack : attacks) {

            // Extract each set bit into separate number
            for (int i = 0; i < 64; i++) {

                long iter = 1L << i;

                // Check if we have a bishop attack
                if ((iter & attack) != 0) {
                    // Add it to possible moves
                    possibleMoves.add(iter);
                }
            }
        }

        return possibleMoves;
    }
}
