import java.util.ArrayList;

public class Board {

    /**
     *
     */
    private static final int BOARD_SIZE = 6;

    /**
     * The boards state.
     */
    private char[][] boardState;

    /**
     * The player for the given board.
     */
    private char player;

    /**
     * This boards' parent.
     */
    private Board parent;

    private ArrayList<Board> children;

    /**
     * Creates a board.
     */
    public Board(char player, Board parent, char[][] boardState) {
        this.player = player;
        if (boardState == null) {
            this.boardState = createInitialState();
        } else {
            this.boardState = boardState;
        }
        this.parent = parent;
        // this.utilityValue = calculateMyUtilityValue();
        this.children = new ArrayList<>();
        if (this.parent != null) {
            this.parent.children.add(this);
        }
    }

    public Board(char player) {
        this(player, null, null);
    }

    public boolean isValidMove(int row, int col) {
        if (this.boardState[row][col] == '-') {
            return true;
        }
        return false;
    }

    public ArrayList<Move> getAllPossibleMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int row= 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (isValidMove(row, col)) {
                    moves.add(new Move(row, col));
                }
            }
        }
        return moves;
    }

    public boolean hasMovesLeft() {
        return !getAllPossibleMoves().isEmpty();
    }

    public Board makeMove(Move move) {
        char[][] newBoardState = this.boardState.clone();
        for (int i = 0; i < BOARD_SIZE; i++) {
            newBoardState[i] = this.boardState[i].clone();
        }
        if (this.isValidMove(move.getRow(), move.getCol())) {
            char nextPlayer = this.player == 'X' ? 'O' : 'X';
            Board newBoard = new Board(nextPlayer, this, newBoardState);
            newBoard.createBarrier(move.getRow(), move.getCol());
            newBoardState[move.getRow()][move.getCol()] = this.player;
            return newBoard;
        } else {
            return null;
        }
    }

    private void createBarrier(int row, int col) {
        for (int i= row - 1; i <= row + 1; i++) {
            for (int j = col -1; j <= col + 1; j++) {
                if (i < BOARD_SIZE && i >= 0 && j < BOARD_SIZE && j >= 0) {
                    this.boardState[i][j] = '/';
                }
            }
        }
    }

    private char[][] createInitialState() {
        char[][] initialBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                initialBoard[row][col] = '-';
            }
        }
        return initialBoard;
    }

    private int calculateMyUtilityValue(boolean player) {
        int utilityValue = 0;
        int emptyCells = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (this.boardState[row][col] == '-') {
                    emptyCells++;
                }
            }
        }
        utilityValue = player ? -emptyCells : +emptyCells;
        return utilityValue;
    }

    public String toString() {
        StringBuilder boardToString = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boardToString.append(this.boardState[row][col]);
            }
            boardToString.append("\n");
        }
        return boardToString.toString();
    }

    public char[][] getBoardState() {
        return boardState;
    }

    public Board getParent() {
        return parent;
    }

    public char getPlayer() {
        return player;
    }

    public ArrayList<Board> getChildren() {
        return this.children;
    }
}
