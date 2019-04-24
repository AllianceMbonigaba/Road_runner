package RoadRunner_UI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

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



        root.getChildren().add(visuals.pane);

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
