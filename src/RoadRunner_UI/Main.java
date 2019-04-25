package RoadRunner_UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.Q;
import static javafx.scene.input.KeyCode.UP;

public class Main extends Application {



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        UI visuals = new UI("C:\\Users\\ALU STUDENT\\IdeaProjects\\roadRunnerProjectPersonal\\src\\Test Inputs\\sample_test_input_1.txt");
        primaryStage.setTitle("Road Runner");
        StackPane root = new StackPane();

        //visuals.changeImgAt(1, 1, 2);
        //visuals.linkingImage();



        root.getChildren().addAll(visuals.pane, visuals.leftButtons());

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
                    default:

                }
            }
        });

        visuals.undo.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent Onclick) {
                        visuals.undo(); // reset the grid to original
                    }
                }
        );



        // reset everything to original
        visuals.reset.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent Onclick) {
                        visuals.reset(); // reset the grid to original
                    }
                }
        );


        // enable and disable 8 directions
        visuals.alldirection.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent Onclick) {
                        if(visuals.checkAllDirection){
                            visuals.alldirection.setText("Enable 8 Directions");
                            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                @Override
                                public void handle(KeyEvent event) {
                                    switch (event.getCode()){

                                case Q:
                                    visuals.moveNW();

                                case W:
                                    visuals.moveNE();
                                case A:
                                    visuals.moveSW();
                                case S:
                                    visuals.moveSE();

                                }
                             }
                        });
                        visuals.checkAllDirection = false;

                        } else{
                            visuals.alldirection.setText("Disable 8 Directions");
                            visuals.checkAllDirection = true;
                        }
                    }
                }
        );






        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
