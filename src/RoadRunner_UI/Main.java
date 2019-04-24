package RoadRunner_UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Road Runner");
        StackPane root = new StackPane();
        UI visuals = new UI("C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Test Inputs\\sample_test_input_1.txt");
        //visuals.changeImgAt(1, 1, 2);
        //visuals.linkingImage();
        visuals.moveUp();
        visuals.moveUp();
        visuals.moveDown();
        visuals.moveDown();
        visuals.moveDown();




        root.getChildren().add(visuals.pane);



        primaryStage.setScene(new Scene(root, 550, 600));
        primaryStage.show();


    }
}
