/** Class to implement A* Algorithm*/

package RoadRunner_Logic;

import java.util.*;

public class AStar {

    /** Initializing fields*/

    boolean allowed8Directions = false;

    int hvCost;
    int diagonalCost;

    Node initialNode;
    Node finalNode;

    Node[][] searchArea;
    PriorityQueue<Node> openList;
    Set<Node> closedSet;

    static int DEFAULT_HV_COST = 10; // Horizontal - Vertical Cost
    static int DEFAULT_DIAGONAL_COST = 14;


    /** Creating the constructor*/
    public AStar(int rows, int columns, Node start, Node finalNode, int hvCost, int diagonalCost) {
        this.hvCost = hvCost;
        this.diagonalCost = diagonalCost;
        setInitialNode(start);
        setFinalNode(finalNode);
        this.searchArea = new Node[rows][columns];
        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Integer.compare(node0.getF(), node1.getF());
            }
        });
        setNodes();
        this.closedSet = new HashSet<>();
    }

    /** Initializing the constructor*/
    public AStar(int rows, int cols, Node initialNode, Node finalNode) {
        this(rows, cols, initialNode, finalNode, DEFAULT_HV_COST, DEFAULT_DIAGONAL_COST);
    }


    /** Method to return the path of the algorithm
     * Time Complexity: O(n)*/
    public List<Node> findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<Node>();
    }

    /** Method to set score for each node
     * Time Complexity: O(n*m)
     * Space Complexity: O(n+m)
     * Auxiliary Space: O(1)*/
    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(getFinalNode());
                this.searchArea[i][j] = node;
            }
        }
    }

    
    public void setBlocks(int[][] blocksArray) {
        for (int i = 0; i < blocksArray.length; i++) {
            int row = blocksArray[i][0];
            int col = blocksArray[i][1];
            setBlock(row, col);
        }
    }

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Node currentNode) {
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    public void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            if (col - 1 >= 0) {
                if (allowed8Directions){
                    checkNode(currentNode, col - 1, lowerRow, getDiagonalCost());
                }
            }
            if (col + 1 < getSearchArea()[0].length) {
                if (allowed8Directions){
                    checkNode(currentNode, col + 1, lowerRow, getDiagonalCost());
                }
            }
            checkNode(currentNode, col, lowerRow, getHvCost());
        }
    }

    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int middleRow = row;
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, getHvCost());
        }
        if (col + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, col + 1, middleRow, getHvCost());
        }
    }

    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int upperRow = row - 1;
        if (upperRow >= 0) {
            if (col - 1 >= 0) {
                if (allowed8Directions){
                    checkNode(currentNode, col - 1, upperRow, getDiagonalCost());
                }
            }
            if (col + 1 < getSearchArea()[0].length) {
                if (allowed8Directions){
                    checkNode(currentNode, col + 1, upperRow, getDiagonalCost());
                }
            }
            checkNode(currentNode, col, upperRow, getHvCost());
        }
    }

    private void checkNode(Node currentNode, int col, int row, int cost) {
        Node adjacentNode = getSearchArea()[row][col];
        if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {
            if (!getOpenList().contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode, cost);
                getOpenList().add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    /** Method checking the final node*/
    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

    /** Methode that checks if there's no node in our priority queue*/
    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }

    /** Method that assign a grid as a block*/
    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    /** Method to get the starting node*/
    public Node getInitialNode() {
        return initialNode;
    }

    /** Method to set the starting node*/
    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    /** Method to get the final node*/
    public Node getFinalNode() {
        return finalNode;
    }

    /** Method to set a node as the final*/
    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    /** Method to return the searching area*/
    public Node[][] getSearchArea() {
        return searchArea;
    }

//    public void setSearchArea(Node[][] searchArea) {
//        this.searchArea = searchArea;
//    }

    /** Method to return nodes in the queue (open list)*/
    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    /** Method to setup the open list*/
    public void setOpenList(PriorityQueue<Node> openList) {
        this.openList = openList;
    }

    /** Method to get the closed set*/
    public Set<Node> getClosedSet() {
        return closedSet;
    }

    /** Method to set the set as closed*/
    public void setClosedSet(Set<Node> closedSet) {
        this.closedSet = closedSet;
    }

    /** Method to get the horizontal-vertical cost*/
    public int getHvCost() {
        return hvCost;
    }

    /** Method to set the horizontal-vertical cost*/
    public void setHvCost(int hvCost) {
        this.hvCost = hvCost;
    }

    /** Method to get the diagonal cost*/
    public int getDiagonalCost() {
        return diagonalCost;
    }

    /** Method to set the diagonal cost*/
    public void setDiagonalCost(int diagonalCost) {
        this.diagonalCost = diagonalCost;
    }
}
