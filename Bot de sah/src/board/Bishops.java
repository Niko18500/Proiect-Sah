package board;

import common.Square;
import common.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bishops extends Pieces {

    // Rays
    private Map<String, List<Long>> rays;

    public Map<String, List<Long>> getRays() {
        return rays;
    }

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
    @Override
    public List<Long> generateMoves(Board board) {
        // Stores bishops' possible moves
        List<Long> possibleMoves = new ArrayList<>();

        // Generate Rays (4 directii pt fiecare patratel)
        long maskedBlockers;

        // Get bishop current position
//        for (long i = 1L; i != 0; i = i << 1) {
//            // If we have a bishop there
//            if ((positions & i) != 0) {
//
//            }
//        }

        long iter = 1L;
        long attacks = 0;

        for (int i = 0; i < 64; i++) {
            if ((iter & positions) != 0) {
                long blockers;

                blockers = pop_bit(board.getAllPositions(), i);

                maskedBlockers = rays.get("SE").get(i) & blockers;

                int bsfOp = Utilities.bitScanForward(maskedBlockers);

//                int bsfMy = Utilities.bitScanForward(rays.get("SE").get(i) & pop_bit(board.getMyPositions(), i));

                long ceva = 1L << bsfOp;

                if ((ceva & board.getOpponentPositions()) != 0) {
                    // E piesa oponentului si o putem lua
                    attacks = rays.get("SE").get(i) & ~rays.get("SE").get(bsfOp);
//                } else {
//                    // E piesa mea si nu o pot lua
//                    attacks = rays.get("NE").get(i) & ~rays.get("NE").get(bsfMy);
//                }
                }

                // pt sud trb cel ai semnificativ (in loc de bsf) -- si la nord invers evident
                // aici a mers (h2h3) pt ca nu era decat un singur bit pe aia hehe)


            }
            iter = iter << 1;
        }


        possibleMoves.add(attacks);

        return possibleMoves;
    }
}
