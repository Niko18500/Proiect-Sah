import java.io.*;

public class XBoard {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private XBoard() {
    }

    public static void handshake() throws IOException {
        // Xboard
        reader.readLine();

        // Proto
        reader.readLine();

        System.out.println("feature sigint=0 san=0 name=SuperBot done=1\n");

        // 4 x accept feature
        for (int i = 0; i < 4; i++) {
            reader.readLine();
        }

        // New
        reader.readLine();

        // Random + level 40 5 0 + post + hard
        for (int i = 0; i < 4; i++) {
            reader.readLine();
        }
    }

    public static void interpretMove(CurrentMove currentMove) throws IOException {
        currentMove.setTime(reader.readLine());
        currentMove.setOtim(reader.readLine());
        currentMove.setMove(reader.readLine());
        currentMove.registerMove();
    }

    public static void sendMove(final String move) {
        System.out.println("move " + move + "\n");
    }

    public static void cleanup() throws IOException {
        reader.close();
    }
}
