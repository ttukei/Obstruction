public class Move {

    private int row;
    private int col;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Move(String input) {
        this.row = input.charAt(0) - '0' - 1;
        this.col = input.charAt(2) - '0' - 1;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public String toString() {
        return (this.row + 1) + "/" + (this.col + 1);
    }

}
