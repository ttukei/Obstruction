import java.util.*;
public class Tester {

    private static char opponent;
    private static char AI;
    private static Board currentBoard;
    private static Scanner sc;
    public static void main(String... args) {
        System.out.println(
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄         ▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄ \n" +
                        "▐░░░░░░░░░░░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌\n" +
                        "▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀▀▀  ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌░▌     ▐░▌\n" +
                        "▐░▌       ▐░▌▐░▌       ▐░▌▐░▌               ▐░▌     ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌               ▐░▌          ▐░▌     ▐░▌       ▐░▌▐░▌▐░▌    ▐░▌\n" +
                        "▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄      ▐░▌     ▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░▌               ▐░▌          ▐░▌     ▐░▌       ▐░▌▐░▌ ▐░▌   ▐░▌\n" +
                        "▐░▌       ▐░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌     ▐░▌     ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌               ▐░▌          ▐░▌     ▐░▌       ▐░▌▐░▌  ▐░▌  ▐░▌\n" +
                        "▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀▀▀▀▀▀█░▌     ▐░▌     ▐░█▀▀▀▀█░█▀▀ ▐░▌       ▐░▌▐░▌               ▐░▌          ▐░▌     ▐░▌       ▐░▌▐░▌   ▐░▌ ▐░▌\n" +
                        "▐░▌       ▐░▌▐░▌       ▐░▌          ▐░▌     ▐░▌     ▐░▌     ▐░▌  ▐░▌       ▐░▌▐░▌               ▐░▌          ▐░▌     ▐░▌       ▐░▌▐░▌    ▐░▌▐░▌\n" +
                        "▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌ ▄▄▄▄▄▄▄▄▄█░▌     ▐░▌     ▐░▌      ▐░▌ ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄      ▐░▌      ▄▄▄▄█░█▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░▌     ▐░▐░▌\n" +
                        "▐░░░░░░░░░░░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌     ▐░▌     ▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌\n" +
                        " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀   ▀▀▀▀▀▀▀▀▀▀▀       ▀       ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀       ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀        ▀▀ \n" +
                        "                                                                                                                                               ");

        opponent = args[0].charAt(0);
        AI = opponent == '1' ? '2' : '1';
        String searchMethod = args[1];

        Board initialBoard;
        currentBoard = new Board('O');
        initialBoard = currentBoard;
        System.out.println(currentBoard);

        sc = new Scanner(System.in);

        boolean didPlayer1Win = false;
        boolean didPlayer2Win = false;

        while(currentBoard.hasMovesLeft()) {
            didPlayer1Win = singlePlayerTurn('1');
            if (currentBoard.hasMovesLeft()) {
                didPlayer2Win = singlePlayerTurn('2');
            }
            System.out.print(currentBoard);
            System.out.println();

        }
        if (didPlayer1Win) {
            System.out.println("Player 1 won!");
        } else {
            System.out.println("Player 2 won!");
        }
        System.out.println(initialBoard.getChildren().size());
    }

    private static boolean singlePlayerTurn(char player) {
        if (player == opponent) {
            return humanTurn();
        } else {
            return AITurn(currentBoard);
        }
    }

    private static boolean humanTurn() {
        boolean success = false;
        while (!success) {
            System.out.println("Your turn");
            System.out.println("Pick a row and column (like so row/col): ");
            String input = sc.next();
            Move move = new Move(input);
            Board board = currentBoard.makeMove(move);
            success = board != null;
            if (!success) {
                System.out.println("Space is already taken!");
            } else {
                currentBoard = board;
            }
        }

        return !currentBoard.hasMovesLeft();
    }

    private static boolean AITurn(Board theCurrentBoard) {
        Solver solver = new Solver(currentBoard, 3, AI);
        Move move = solver.getBestMoveForAI(currentBoard);
        currentBoard = theCurrentBoard.makeMove(move);
        System.out.println("CPU turn");
        System.out.println("CPU choose to move: " + move);
        return !currentBoard.hasMovesLeft();
    }
}
