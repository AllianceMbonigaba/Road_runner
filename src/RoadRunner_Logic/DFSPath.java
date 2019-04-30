package RoadRunner_Logic;

import java.util.List;
import java.util.Stack;

public class DFSPath {

    DFSVertex start;
    DFSVertex end;
    boolean allowed8Directions = false;

    public Stack<DFSVertex> stack;

    public DFSPath(){
        this.stack = new Stack<>();
    }

    public void dfs(List<DFSVertex> vertexList){

        for (DFSVertex v: vertexList){
            if (!v.isVisited()){
                v.setVisited(true);
                DFSwithStack(v);
            }
        }
    }

    public void DFSwithStack(DFSVertex rootVertex){
        this.stack.add(rootVertex);
        rootVertex.setVisited(true);

        while (!stack.isEmpty()){
            DFSVertex actualVertex = this.stack.pop();
            System.out.println(actualVertex);

            for (DFSVertex v: actualVertex.getNeighbour()){
                if (!v.isVisited()){
                    v.setVisited(true);
                    this.stack.push(v);
                }
            }
        }
    }

    public static void main(String[] args) {
        DFSPath test = new DFSPath();

    }
}
