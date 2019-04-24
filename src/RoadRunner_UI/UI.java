package RoadRunner_UI;

import RoadRunner_Logic.ReadFile;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UI extends Application{


    int[] currentPosRoadRunner = new int[2];
    int[] previousPosRoadRunner = new int[2];

    ArrayList<Object> visited = new ArrayList<>();

    ReadFile file = new ReadFile();
    String text;
    int[][] fileArray;
    int[][] originalFileArray;

    GridPane pane = new GridPane();
    Scene scene = new Scene(pane, 500, 500);

    public UI(String fileParth){
        text = file.read_file(fileParth);
        fileArray = file.toArray(text);
        originalFileArray = file.toArray(text);
        linkingImage();
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
    public void linkingImage(){
        for (int i=0; i<fileArray.length; i++){
            for (int j=0; j<fileArray[i].length; j++){

                int number = fileArray[i][j];

                // get current position of the rood runner / start image at index 8
                if(number == 8){


                    currentPosRoadRunner[0] = i; // row

                    currentPosRoadRunner[1] = j; // column

                }

                Image image = new Image(imageArray[number]);
                ImageView view = new ImageView(image);
                view.setPreserveRatio(true);
                view.setFitHeight(100);
                view.setFitWidth(100);

                pane.setHgap(5);
                pane.setVgap(5);
                pane.setAlignment(Pos.CENTER);


                pane.add(view, j, i);
            }

        }
    }

    public boolean checkBoundary(){
       if (currentPosRoadRunner[0] > 0 || currentPosRoadRunner[0] < file.rowSize || currentPosRoadRunner[1] > 0 || currentPosRoadRunner[1] < file.columnSize){
           return true;
       }
       return false;
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
        int[] arr = {row, column};
        if(visited.contains(arr)){
            return true;
        }
        return false;
    }

    public void putAltImage(int imgIndexTochange){
        if(imgIndexTochange != 1 && imgIndexTochange != 8 && imgIndexTochange != 9 && imgIndexTochange != 7){
            changeImgAt(currentPosRoadRunner[0], currentPosRoadRunner[1], imgIndexTochange + 10);
            System.out.println("this is the one running");
            System.out.println(originalFileArray[previousPosRoadRunner[0]][previousPosRoadRunner[1]]);
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
        if (checkBoundary() && !(isVisited(currentPosRoadRunner[0]- 1, currentPosRoadRunner[1]))){
            switchToPrevious();


            // hold the image index to be changed
            int toBeChanged = fileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];


            // put alt image
            putAltImage(toBeChanged);

            changeImgAt(currentPosRoadRunner[0] - 1, currentPosRoadRunner[1], 7);
            int[] visitedCell = {currentPosRoadRunner[0] - 1, currentPosRoadRunner[1]};
            currentPosRoadRunner[0] = currentPosRoadRunner[0] - 1;
            visited.add(visitedCell);

        }

    }

    public void moveDown(){

    }



    /** Creating the stage*/
    @Override
    public void start(Stage primaryStage) {

    }

}
