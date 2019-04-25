package RoadRunner_Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadFile {

    public int rowSize;
    public int columnSize;

    ArrayList<String> path = new ArrayList<>();
    ArrayList<Integer> previousNumber = new ArrayList<>();



    /** Method to read the file and return a string containing words*/
    public String read_file(String filePath) {
        String text = "";
        File file = new File(filePath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line!= null){
                text += line;
                line = br.readLine();
                text += "\n";
            }
            br.close();
        } catch (Exception e){
            System.out.println("reading file failed");
            return "";
        }
        return text;
    }

    /** Method turning the file read to a 2D array*/
    public int[][] toArray(String text){

        String[] textArray =  text.split("\n");
        String[] arraySize = textArray[0].split(" ");

        rowSize = Integer.parseInt(arraySize[0].trim());
        columnSize = Integer.parseInt(arraySize[1].trim());

        int[][] fileArray = new int[rowSize][columnSize];

        for (int i=0; i<fileArray.length; i++){
            String[] arrayElement = textArray[i+1].split("");
            for (int j=0; j<fileArray[i].length; j++){
                int number = Integer.parseInt(arrayElement[j].trim());
                fileArray[i][j] = number;
            }
        }
        return fileArray;
    }


    /** Method allowing the road runner to move up*/
    public void moveUp(int[][] fileArray, int row, int column){
        path.add(row + " " + column);
        row = row - 1;
        int number = fileArray[row][column];
        previousNumber.add(number);

        if (number != 1 && row > 0){
            fileArray[row][column] = 7;
            if (number != 8 && number != 9){
                fileArray[row][column] = previousNumber.get(0) + 10;
            }

        }
        System.out.println(previousNumber);
    }

    /** Method to track the score*/
    public int score(){
        HashMap<Integer, Integer> score = new HashMap<>();
        score.put(0, -1);
        score.put(2, -2);
        score.put(3, -4);
        score.put(4, -8);
        score.put(5, 1);
        score.put(6, 5);

        int measureScore = 0;


        return measureScore;
    }



//    public int[][] moveDown(int row, int column){
//        int[][]fileArray = toArray(read_file("C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Test Inputs\\sample_test_input_1.txt"));
//        int position = fileArray[row][column];
//        if (row >  && position != 1){
//            fileArray[row][column] = position + 10;
//            row --;
//            fileArray[row][column] = 7;
//        }
//
//        return fileArray;
//    }

    public static void main(String[] args) {

        ReadFile test = new ReadFile();
        String text = test.read_file("C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Test Inputs\\sample_test_input_1.txt");
        int[][] fileArray = test.toArray(text);

        Node start = new Node(3, 1);
        Node end = new Node(0, 4);
        int rows = test.rowSize + 1;
        int cols = test.columnSize + 1;
        System.out.println("rows:" + rows + ", columns:"+cols );
        AStar aStar = new AStar(rows, cols, start, end);
        int[][] blocksArray = new int[][]{{0, 0}};
        aStar.setBlocks(blocksArray);
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            System.out.println(node);
        }


    }
}
