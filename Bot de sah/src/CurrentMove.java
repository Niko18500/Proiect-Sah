import board.Board;
import common.Constants;
import common.Square;

public class CurrentMove {

    private String time;

    private String otim;

    private String move;

    private Board board;

    public CurrentMove(Board board) {
        this.board = board;
    }

    public String getTime() {
        return time;
    }

    public String getOtim() {
        return otim;
    }

    public String getMove() {
        return move;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setOtim(String otim) {
        this.otim = otim;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public void registerMove() {
        String firstMove = move.substring(0, 2);
        String secondMove = move.substring(2, 4);

        long moveToApply = (1L << Square.valueOf(firstMove).bits) | (1L << Square.valueOf(secondMove).bits);
        // trb sa updatam bitboard urile (toate) a i sa stim care piesa s a mutat
    }
}
