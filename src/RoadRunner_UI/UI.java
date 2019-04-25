package RoadRunner_UI;

import RoadRunner_Logic.ReadFile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UI extends Application{

    int allowUndo = 0;
    int[] holdLastPos = new int[1];
    public Button reset = new Button("Reset");
    public Button redo = new Button("Redo");
    public Button undo = new Button("Undo");
    public Button alldirection = new Button("Enable 8 Directions");

    public Button A = new Button("Solve with A*");
    public Button dijksta = new Button("Solve with Dijksta");
    public Button dfs = new Button("Solve with DFS");


    int[] currentPosRoadRunner = new int[2];
    int[] previousPosRoadRunner = new int[2];
    public Boolean checkAllDirection = false;

    ArrayList<Integer[]> visited = new ArrayList<>();

    ReadFile file = new ReadFile();
    String text;
    int[][] fileArray;
    int[][] originalFileArray;
    int[][] toHome;


    GridPane pane = new GridPane();
    Scene scene = new Scene(pane, 500, 500);

    public UI(String fileParth){
        text = file.read_file(fileParth);
        fileArray = file.toArray(text);
        originalFileArray = file.toArray(text);


        linkingImage(fileArray);
    }


    //
    /* Image array Kevin path
    String[] imageArray = {"file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\road.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\boulder.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\pothole.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\explosive.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\coyote.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\tarred.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\gold.jpg",
            "",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\start.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\goal.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\coyote_alt.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\explosive_alt.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\gold_alt.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\pothole_alt.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\road_alt.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\road_runner.jpg",
            "file:C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Image Files\\tarred_alt.jpg"};
    */

    // Image array Alliance's path
    String[] imageArray = {"file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\road.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\boulder.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\pothole.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\explosive.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\coyote.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\tarred.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\gold.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\road_runner.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\start.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\goal.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\road_alt.jpg",
            "",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\pothole_alt.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\explosive_alt.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\coyote_alt.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\tarred_alt.jpg",
            "file:C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Image Files\\gold_alt.jpg"
            };




    public static void main(String[] args) {
        launch(args);
    }

    /** Method linking the images in their respective array position*/
    public void linkingImage(int[][] _fileArray){
        for (int i=0; i<_fileArray.length; i++){
            for (int j=0; j<_fileArray[i].length; j++){

                int number = _fileArray[i][j];

                // get current position of the rood runner / start image at index 8
                if(number == 8){


                    currentPosRoadRunner[0] = i; // row

                    currentPosRoadRunner[1] = j; // column

                    Integer[] visitedCell = {currentPosRoadRunner[0], currentPosRoadRunner[1]};

                    visited.add(visitedCell);

                }

                Image image = new Image(imageArray[number]);
                ImageView view = new ImageView(image);
                view.setPreserveRatio(true);
                view.setFitHeight(100);
                view.setFitWidth(100);

                pane.setHgap(5);
                pane.setVgap(5);
                pane.setAlignment(Pos.CENTER);
                pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));



                pane.add(view, j, i);
            }

        }
    }

    public GridPane leftButtons(){
        GridPane buttons = new GridPane();

        buttons.setVgap(5);
        buttons.setHgap(5);
        buttons.setAlignment(Pos.BOTTOM_LEFT);

        buttons.add(undo, 0, 0);
        buttons.add(redo, 0, 1);
        buttons.add(alldirection, 0, 2);
        buttons.add(reset, 0, 3);


        return buttons;
    }

    public void reset(){
        visited.clear();
        linkingImage(originalFileArray);
    }





    // adding image at given position in the Gridpane
    public void changeImgAt(int row, int column, int imgIndex){
        Image image = new Image(imageArray[imgIndex]);
        ImageView view = new ImageView(image);


        view.setPreserveRatio(true);
        view.setFitHeight(100);
        view.setFitWidth(100);


        pane.add(view, column, row);

        fileArray[row][column] = imgIndex;
    }

    public Boolean isVisited(int row, int column){


        for(int i = 0 ; i < visited.size(); i++){

            if(visited.get(i)[0] == row && visited.get(i)[1] == column){
                return true;
            }
        }

       return false;
    }

    public void putAltImage(int imgIndexTochange){
        if(imgIndexTochange != 1 && imgIndexTochange != 8 && imgIndexTochange != 9 && imgIndexTochange != 7){
            changeImgAt(currentPosRoadRunner[0], currentPosRoadRunner[1], imgIndexTochange + 10);

        }
        else {

            changeImgAt(previousPosRoadRunner[0], previousPosRoadRunner[1], originalFileArray[previousPosRoadRunner[0]][previousPosRoadRunner[1]]);

        }




    }

    public void switchToPrevious(){
        previousPosRoadRunner[0] = currentPosRoadRunner[0];
        previousPosRoadRunner[1] = currentPosRoadRunner[1];
    }



    public void moveUp(){
        if (currentPosRoadRunner[0] > 0 && !(isVisited(currentPosRoadRunner[0]- 1, currentPosRoadRunner[1]))){
            switchToPrevious();


            // hold the image index to be changed
            int toBeChanged = originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];


            // put alt image
            putAltImage(toBeChanged);

            changeImgAt(currentPosRoadRunner[0] - 1, currentPosRoadRunner[1], 7);
            Integer[] visitedCell = {currentPosRoadRunner[0] - 1, currentPosRoadRunner[1]};
            currentPosRoadRunner[0] = currentPosRoadRunner[0] - 1;
            visited.add(visitedCell);

        }

    }

    public void moveDown(){
        if (currentPosRoadRunner[0] < file.rowSize - 1 && !(isVisited(currentPosRoadRunner[0] + 1, currentPosRoadRunner[1]))){
            switchToPrevious();


            // hold the image index to be changed
            int toBeChanged = originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];


            // put alt image
            putAltImage(toBeChanged);

            changeImgAt(currentPosRoadRunner[0] + 1, currentPosRoadRunner[1], 7);
            Integer[] visitedCell = {currentPosRoadRunner[0] + 1, currentPosRoadRunner[1]};
            currentPosRoadRunner[0] = currentPosRoadRunner[0] + 1;
            visited.add(visitedCell);

        }

    }

    public void moveLeft(){
        if (currentPosRoadRunner[1] > 0 && !(isVisited(currentPosRoadRunner[0], currentPosRoadRunner[1] - 1))){
            switchToPrevious();


            // hold the image index to be changed
            int toBeChanged = originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];


            // put alt image
            putAltImage(toBeChanged);

            changeImgAt(currentPosRoadRunner[0], currentPosRoadRunner[1] - 1, 7);
            Integer[] visitedCell = {currentPosRoadRunner[0], currentPosRoadRunner[1] - 1};
            currentPosRoadRunner[1] = currentPosRoadRunner[1] - 1;
            visited.add(visitedCell);

        }
    }

    public void moveRight(){
        if (currentPosRoadRunner[1] < file.columnSize - 1 && !(isVisited(currentPosRoadRunner[0], currentPosRoadRunner[1] + 1))){
            switchToPrevious();


            // hold the image index to be changed
            int toBeChanged = originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];


            // put alt image
            putAltImage(toBeChanged);

            changeImgAt(currentPosRoadRunner[0], currentPosRoadRunner[1] + 1, 7);
            Integer[] visitedCell = {currentPosRoadRunner[0], currentPosRoadRunner[1] + 1};
            currentPosRoadRunner[1] = currentPosRoadRunner[1] + 1;
            visited.add(visitedCell);

        }
    }

    public void moveNW(){
        if ((currentPosRoadRunner[0] > 0 && currentPosRoadRunner[1] > 0) && !(isVisited(currentPosRoadRunner[0] - 1, currentPosRoadRunner[1] - 1))){
            switchToPrevious();


            // hold the image index to be changed
            int toBeChanged = originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];


            // put alt image
            putAltImage(toBeChanged);

            changeImgAt(currentPosRoadRunner[0] - 1, currentPosRoadRunner[1] - 1, 7);
            Integer[] visitedCell = {currentPosRoadRunner[0] - 1, currentPosRoadRunner[1] - 1};
            currentPosRoadRunner[1] = currentPosRoadRunner[1] - 1;
            currentPosRoadRunner[0] = currentPosRoadRunner[1] - 1;
            visited.add(visitedCell);

        }
    }

    public void moveNE(){
        if ((currentPosRoadRunner[0] > 0 && currentPosRoadRunner[1] < file.columnSize - 1) && !(isVisited(currentPosRoadRunner[0] - 1, currentPosRoadRunner[1] + 1))){
            switchToPrevious();


            // hold the image index to be changed
            int toBeChanged = originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];


            // put alt image
            putAltImage(toBeChanged);

            changeImgAt(currentPosRoadRunner[0] - 1, currentPosRoadRunner[1] + 1, 7);
            Integer[] visitedCell = {currentPosRoadRunner[0] - 1, currentPosRoadRunner[1] + 1};
            currentPosRoadRunner[1] = currentPosRoadRunner[1] + 1;
            currentPosRoadRunner[0] = currentPosRoadRunner[1] - 1;
            visited.add(visitedCell);

        }


    }


    public void moveSW(){
        if ((currentPosRoadRunner[0] < file.rowSize - 1 && currentPosRoadRunner[1] > 0) && !(isVisited(currentPosRoadRunner[0] + 1, currentPosRoadRunner[1] - 1))){
            switchToPrevious();


            // hold the image index to be changed
            int toBeChanged = originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];


            // put alt image
            putAltImage(toBeChanged);

            changeImgAt(currentPosRoadRunner[0] + 1, currentPosRoadRunner[1] - 1, 7);
            Integer[] visitedCell = {currentPosRoadRunner[0] + 1, currentPosRoadRunner[1] - 1};

            currentPosRoadRunner[0] = currentPosRoadRunner[1] + 1;
            currentPosRoadRunner[1] = currentPosRoadRunner[1] - 1;

            visited.add(visitedCell);

        }

    }


    public void undo(){
        try{
            int rowToMoveTo = visited.get(visited.size() - 2)[0];
            int columnToMoveTo = visited.get(visited.size() - 2)[1];

            changeImgAt(rowToMoveTo, columnToMoveTo, 7);
            currentPosRoadRunner[0] = rowToMoveTo;
            currentPosRoadRunner[1] = columnToMoveTo;
            System.out.println(visited.size());


            int lastRow = visited.get(visited.size() - 1)[0];
            int lastColumn = visited.get(visited.size() - 1)[1];
            int originalImg = originalFileArray[lastRow][lastColumn];
            changeImgAt(lastRow, lastColumn, originalImg);

            holdLastPos[0] = lastRow;
            holdLastPos[1] = lastColumn;

            visited.remove(visited.size() - 1);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("index out of bound");
        }






    }


    public void moveSE(){
        if ((currentPosRoadRunner[0] < file.rowSize - 1 && currentPosRoadRunner[1] < file.columnSize -1) && !(isVisited(currentPosRoadRunner[0] + 1, currentPosRoadRunner[1] + 1))){
            switchToPrevious();


            // hold the image index to be changed
            int toBeChanged = originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];


            // put alt image
            putAltImage(toBeChanged);

            changeImgAt(currentPosRoadRunner[0] + 1, currentPosRoadRunner[1] + 1, 7);
            Integer[] visitedCell = {currentPosRoadRunner[0] + 1, currentPosRoadRunner[1] + 1};

            currentPosRoadRunner[0] = currentPosRoadRunner[1] + 1;
            currentPosRoadRunner[1] = currentPosRoadRunner[1] + 1;

            visited.add(visitedCell);

        }

    }



    /** Creating the stage*/
    @Override
    public void start(Stage primaryStage) {

    }

}
