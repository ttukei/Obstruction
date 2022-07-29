import java.util.ArrayList;

public class Solver {

    /**
     * The maximum number of nodes expanded
     */
    private int nodesExpanded;

    private int maxDepth;

    private Board board;

    private char player;

    public Solver(Board initialBoard, int maxDepth, char player) {
        this.board = initialBoard;
        this.maxDepth = maxDepth;
        this.player = player;
    }

    public Board buildGameTree(int currentDepth, Board rootNode) {
        ArrayList<Move> availableMoves = rootNode.getAllPossibleMoves();
        if (availableMoves.isEmpty() || currentDepth == 0) {
            return rootNode;
        }

        for (Move move: availableMoves) {
            Board childBoard = rootNode.makeMove(move);
            buildGameTree(currentDepth - 1, childBoard);
        }
        return rootNode;
    }

    public Move getBestMoveForAI(Board currentBoard) {
        ArrayList<Move> possibleMoves = currentBoard.getAllPossibleMoves();
        Move bestMoveSoFar = null;
        double maxValueSoFar = Double.NEGATIVE_INFINITY;
        for (Move move : possibleMoves) {
            Board afterMove = currentBoard.makeMove(move);
            Board gameTree = buildGameTree(this.maxDepth, afterMove);
            double val = getMiniMax(gameTree, this.maxDepth);
            if (val > maxValueSoFar) {
                bestMoveSoFar = move;
                maxValueSoFar = val;
            }
        }
        return bestMoveSoFar;
    }

    private double getMiniMax(Board node, int currentDepth) {
        if (currentDepth == 0 || !node.hasMovesLeft()) {
            int utility = node.getAllPossibleMoves().size();
            if (node.getPlayer() != this.player) {
                utility = utility * -1;
            }
            return utility ;
        }
        ArrayList<Board> children = node.getChildren();
        if (node.getPlayer() == this.player) {
            double val = Double.NEGATIVE_INFINITY;
            for (Board child : children) {
                val = Math.max(val, getMiniMax(child, currentDepth - 1));
            }
            return val;
        } else {
            double val = Double.POSITIVE_INFINITY;
            for (Board child : children) {
                val = Math.min(val, getMiniMax(child, currentDepth - 1));
            }
            return val;
        }
    }
}
