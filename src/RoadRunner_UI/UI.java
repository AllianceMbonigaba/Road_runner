package RoadRunner_UI;

import RoadRunner_Logic.ReadFile;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class UI extends Application{


    ReadFile file = new ReadFile();
    String text = file.read_file("C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Test Inputs\\sample_test_input_1.txt");
    int[][] fileArray = file.toArray(text);

    GridPane pane = new GridPane();
    Scene scene = new Scene(pane, 500, 500);

    // Image array keeping path
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



    public static void main(String[] args) {
        launch(args);
    }

    /** Method linking the images in their respective array position*/
    public void linkingImage() throws Exception{
        for (int i=0; i<fileArray.length; i++){
            for (int j=0; j<fileArray[i].length; j++){
                int number = fileArray[i][j];
                System.out.print(number);
                Image image = new Image(imageArray[number]);
                ImageView view = new ImageView(image);
                view.setPreserveRatio(true);
                view.setFitHeight(100);
                view.setFitWidth(100);

                pane.add(view, j, i);
            }
            System.out.println("\n");
        }
    }

    /** Creating the stage*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        linkingImage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }





}
