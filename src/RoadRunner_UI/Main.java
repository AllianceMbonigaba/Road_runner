package RoadRunner_UI;

import RoadRunner_Logic.Node;
import RoadRunner_Logic.ReadFile;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Road Runner");
        StackPane root = new StackPane();
        UI visuals = new UI("C:\\Users\\ALU Student 100\\IdeaProjects\\Road_runner_Kevin&Alliance\\src\\Test Inputs\\sample_test_input_1.txt");
        //visuals.changeImgAt(1, 1, 2);
        //visuals.linkingImage();



        root.getChildren().addAll(visuals.pane, visuals.leftButtons());

        ArrayList<String> path = visuals.astarPath(new Node(3, 1), new Node(0, 3));
        for (int i=0; i<path.size(); i++){
            System.out.println(path.get(i));
        }

        Scene scene = new Scene(root, 550, 600);


        // move roan runner upon four directions on keypress
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        visuals.moveUp(); break;
                    case DOWN:
                        visuals.moveDown(); break;
                    case LEFT:
                        visuals.moveLeft(); break;
                    case RIGHT:
                        visuals.moveRight(); break;

                }
            }
        });




        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
