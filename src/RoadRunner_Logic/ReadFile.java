package RoadRunner_Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFile {

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

        int row = Integer.parseInt(arraySize[0].trim());
        int column = Integer.parseInt(arraySize[1].trim());

        int[][] fileArray = new int[row][column];

        for (int i=0; i<fileArray.length; i++){
            String[] arrayElement = textArray[i+1].split("");
            for (int j=0; j<fileArray[i].length; j++){
                int number = Integer.parseInt(arrayElement[j].trim());
                fileArray[i][j] = number;
            }
        }
        return fileArray;
    }

    public static void main(String[] args) {

        ReadFile test = new ReadFile();
        String text = test.read_file("C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Test Inputs\\sample_test_input_1.txt");
        int[][] fileArray = test.toArray(text);

        for (int i=0; i< fileArray.length; i++){
            System.out.print(fileArray[1][i]);
        }

    }
}
