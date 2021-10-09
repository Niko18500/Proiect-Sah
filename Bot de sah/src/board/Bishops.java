package board;

import common.Square;
import common.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bishops extends Pieces {

    // Rays
    private Map<String, List<Long>> rays;

    // Attacks
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
            set_bit(Square.valueOfPosition("c1").bits);
            set_bit(Square.valueOfPosition("f1").bits);
        } else {
            set_bit(Square.valueOfPosition("c8").bits);
            set_bit(Square.valueOfPosition("f8").bits);
        }

        generateRays();
    }

    private void generateRays() {
        rays = new HashMap<>();

        List<Long> raysNE = new ArrayList<>();
        List<Long> raysNW = new ArrayList<>();
        List<Long> raysSE = new ArrayList<>();
        List<Long> raysSW = new ArrayList<>();


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

    // https://www.rhysre.net/fast-chess-move-generation-with-magic-bitboards.html

    private void generateAttacks(Board board) {
        // Idee: o functie care extrage fiecare bit de 1 din attacks (pt ca ne trb fiecare mutare separata)


        // Stores bishops' possible moves
        attacks = new ArrayList<>();

        // Generate Rays (4 directii pt fiecare patratel)
        long maskedBlockers;

        // Get bishop current position

        List<Integer> bishopSquares = Arrays.asList(Utilities.bitScanLSB(positions),
                                                                Utilities.bitScanMSB(positions));

        for (int bishopSquare : bishopSquares) {

            long allAttacksRay = 0;

            long blockers = pop_bit(board.getAllPositions(), bishopSquare);

            // Lista cu directiile
            List<String> directions = Arrays.asList("NW", "NE", "SW", "SE");

            for (String direction : directions) {

                long attacksRay = 0;

                // pt SE momentan
                maskedBlockers = rays.get(direction).get(bishopSquare) & blockers;

                // Check if N or S (if N -> LSB, if S -> MSB)
                int bsfOp;
                if (direction.equals("NW") || direction.equals("NE")) {
                    bsfOp = Utilities.bitScanLSB(maskedBlockers);
                } else {
                    bsfOp = Utilities.bitScanMSB(maskedBlockers);
                }


                if (bsfOp != -1) {
                    long ceva = 1L << bsfOp;


                    if ((ceva & board.getOpponentPositions()) != 0) {
                        // E piesa oponentului si o putem lua
                        attacksRay = rays.get(direction).get(bishopSquare) &
                                ~rays.get(direction).get(bsfOp);
                    } else if ((ceva & board.getMyPositions()) != 0) {
                        attacksRay = rays.get(direction).get(bishopSquare) &
                                ~rays.get(direction).get(bsfOp);
                        attacksRay = ~ceva & attacksRay;
                    }

                    // No pieces in sight
                } else {
                    attacksRay = rays.get(direction).get(bishopSquare);
                }

                allAttacksRay |= attacksRay;
            }

            attacks.add(allAttacksRay);
        }
    }

    @Override
    public List<Long> generateMoves(Board board) {
        generateAttacks(board);

        // Stores bishops' possible moves
        List<Long> possibleMoves = new ArrayList<>();

        // Iterate through positions (our colored knights on the board)
        for (long attack : attacks) {

            for (int i = 0; i < 64; i++) {

                long iter = 1L << i;

                // Check if we have a bishop attack
                if ((iter & attack) != 0) {
                    possibleMoves.add(iter);
                }
            }
        }

        return possibleMoves;
    }
}
