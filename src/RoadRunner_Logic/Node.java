/** Node class used in AStar*/

package RoadRunner_Logic;


public class Node {

    /** Declaring fields*/
    int g;      // movement cost to move from starting point to a given square
    int h;      // movement cost from a given square to the final destination
    int f;      // Sum of g and h
    int row;
    int column;
    boolean isBlock;    // Checking i the grid is a block
    Node parent;

    /** Creating the node constructor
     * Taking the row and the column indices*/
    public Node(int row, int column) {
        super();
        this.row = row;
        this.column = column;
    }



    /** Method to set the node data and calculate final cost*/
    public void setNodeData(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        setParent(currentNode);
        setG(gCost);
        calculateFinalCost();
    }

    /** Method which checks for the better path*/
    public boolean checkBetterPath(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        if (gCost < getG()) {
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    /** Method to calculate the Heuristic cost*/
    public void calculateHeuristic(Node target) {
        this.h = Math.abs(target.getRow() - getRow()) + Math.abs(target.getCol() - getCol());
    }

    /** Method to calculate the final cost*/
    private void calculateFinalCost() {
        int finalCost = getG() + getH();
        setF(finalCost);
    }


    @Override
    public boolean equals(Object arg0) {
        Node other = (Node) arg0;
        return this.getRow() == other.getRow() && this.getCol() == other.getCol();
    }

    /** A to string method to print a strint in reference of an object*/
    @Override
    public String toString() {
        return "row=" + row + ", col=" + column + "";
    }


    /** Getters and Setters*/
    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return column;
    }

    public void setCol(int col) {
        this.column = col;
    }
}