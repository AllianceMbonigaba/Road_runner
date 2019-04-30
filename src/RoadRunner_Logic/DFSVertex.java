package RoadRunner_Logic;

import java.util.ArrayList;
import java.util.List;

public class DFSVertex {

    public boolean visited;
    public int row;
    public int column;
    public List<DFSVertex> neighbour;

    public DFSVertex(int row, int column){
        this.row = row;
        this.column = column;
        this.neighbour = new ArrayList<>();
    }

    public String toString(){
        return row + ", " + column;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setColumn(int column){
        this.column = column;
    }

    public void addNeighbour(DFSVertex vertex){
        this.neighbour.add(vertex);
    }

    public boolean isVisited(){
        return visited;
    }

    public void setVisited(boolean state){
        this.visited = state;
    }

    public List<DFSVertex> getNeighbour(){
        return this.neighbour;
    }


}
