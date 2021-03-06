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

    /** Method turning the file read to a 2D array
     * Time Complexity: O(n**2)
     * Space Complexity: O(n)*/
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

}
