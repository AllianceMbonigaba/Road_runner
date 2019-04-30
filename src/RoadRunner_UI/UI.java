package RoadRunner_UI;

import RoadRunner_Logic.AStar;
import RoadRunner_Logic.Node;
import RoadRunner_Logic.ReadFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import java.util.*;
import java.util.List;

import static javafx.application.Application.launch;


public class UI extends Application{

    int undocount = 0;


    int checkScore = 0;

    Stack <Integer[]> undoStack = new Stack<>();
    Stack <Integer []> redoStack = new Stack<>();


    public String pathMap = "";
    public Boolean setANewStart = false;


    int[] holdLastPos = new int[1];
    public Button reset = new Button("Reset");
    public Button redo = new Button("Redo");
    public Button undo = new Button("Undo");
    public Button alldirection = new Button("Enable 8 Directions");
    public Button loadNewMap = new Button("Load New Map");
    public Button setSart = new Button("Pick Start");
    public Button score = new Button("Score: " + checkScore);

    public Button A = new Button("Solve with A*");
    public Button dijksta = new Button("Solve with Dijksta");
    public Button dfs = new Button("Solve with DFS");


    // start position and goal position
    public int [] goalPos = new int[2];
    public int [] startPos = new int [2];

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


    public UI(String fileParth){
        text = file.read_file(fileParth);
        fileArray = file.toArray(text);
        originalFileArray = file.toArray(text);
        toHome = file.toArray(text);


        linkingImage(fileArray);
    }

    int[][] blocks = new int[file.rowSize][file.columnSize];
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
    @Override
    public void start(Stage primaryStage){}

    /** Method linking the images in their respective array position*/
    public void linkingImage(int[][] _fileArray){
        for (int i=0; i<_fileArray.length; i++){
            for (int j=0; j<_fileArray[i].length; j++){

                int number = _fileArray[i][j];

                int newSartRow = i;
                int newSartColumn = j;

                int[] g = new int[2];
                g[0] = newSartRow;
                g[1] = newSartColumn;


                // get Road runner goal position
                if (number == 9){
                    goalPos[0] = i; // row
                    goalPos[1] = j; // column

                }

                // get current position of the road runner / start image at index 8
                if(number == 8){

                    startPos[0] = i;
                    startPos[1] = j;
                    currentPosRoadRunner[0] = i; // row

                    currentPosRoadRunner[1] = j; // column

                    Integer[] visitedCell = {currentPosRoadRunner[0], currentPosRoadRunner[1]};

                    visited.add(visitedCell);
                    undoStack.push(visitedCell);

                }

                Image image = new Image(imageArray[number]);
                ImageView view = new ImageView(image);
                view.setPreserveRatio(true);
                view.setFitHeight(50);
                view.setFitWidth(50);


                // set new start
                view.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(setANewStart){

                            // This will execute whenever the image is clicked.
                            visited.clear();
                            undoStack.clear();
                            int imgToReplace = fileArray[newSartRow][newSartColumn];

                            changeImgAt(startPos[0], startPos[1], imgToReplace);
                            originalFileArray[startPos[0]][startPos[1]] = imgToReplace;

                            changeImgAt(newSartRow, newSartColumn, 8);
                            originalFileArray[newSartRow][newSartColumn] = 8;



                            setANewStart = false;


//                        currentPosRoadRunner[0] = newSartRow;
//                        currentPosRoadRunner[1] = newSartColumn;


                        }





                    }
                });

                pane.setHgap(5);
                pane.setVgap(5);
                pane.maxHeight(50);

                pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));



                pane.add(view, j, i);
            }

        }
    }

    GridPane buttons = new GridPane();


    public GridPane leftButtons(){

        buttons.setVgap(5);
        buttons.setHgap(5);





        buttons.setMaxHeight(10);


        buttons.add(undo, 0, 0);
        buttons.add(redo, 0, 1);
        buttons.add(alldirection, 0, 2);
        buttons.add(reset, 0, 3);
        buttons.add(A, 0, 4);
        buttons.add(setSart, 0, 5);
        buttons.add(loadNewMap, 0, 6);
        buttons.add(score, 0, 7);



        return buttons;
    }




    public void reset(){
        visited.clear();
        checkScore = 0;
        score.setText("Score: "+ checkScore);

        if(setANewStart){
            linkingImage(toHome);
        }else {
            linkingImage(originalFileArray);
        }



    }

    public void pickSart(){

    }





    // adding image at given position in the Gridpane
    public void changeImgAt(int row, int column, int imgIndex){
        Image image = new Image(imageArray[imgIndex]);
        ImageView view = new ImageView(image);


        view.setPreserveRatio(true);
        view.setFitHeight(50);
        view.setFitWidth(50);


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
            getscore(imgIndexTochange);
            score.setText("Score: "+ checkScore);
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
            undoStack.push(visitedCell);

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
            undoStack.push(visitedCell);

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
            undoStack.push(visitedCell);

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
            undoStack.push(visitedCell);

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

            currentPosRoadRunner[0] = currentPosRoadRunner[0] - 1;

            visited.add(visitedCell);
            undoStack.push(visitedCell);

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
            currentPosRoadRunner[0] = currentPosRoadRunner[0] - 1;
            visited.add(visitedCell);
            undoStack.push(visitedCell);

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

            currentPosRoadRunner[0] = currentPosRoadRunner[0] + 1;
            currentPosRoadRunner[1] = currentPosRoadRunner[1] - 1;

            visited.add(visitedCell);
            undoStack.push(visitedCell);

        }

    }

    // redo the last action
    public void redo(){
        if(!redoStack.isEmpty()){
            Integer[] goTo = redoStack.pop();
            changeImgAt(goTo[0], goTo[1], 7);
            visited.add(goTo);
            undoStack.push(goTo);

            changeImgAt(currentPosRoadRunner[0], currentPosRoadRunner[1], originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]] + 10);
            currentPosRoadRunner[0] = goTo[0];
            currentPosRoadRunner[1] = goTo[1];
        }
    }


    // setting new start
    public  void setNewStart(){


    }

    // loading a new map
    public void loadNewMap(){
        System.out.println("Copy and past path for the new map on the next line; ");
        Scanner path = new Scanner(System.in);
        String pathValue = path.nextLine();

        text = file.read_file(pathValue);
        fileArray = file.toArray(text);
        originalFileArray = file.toArray(text);

        linkingImage(fileArray);
    }


    public void undo(){


        if((!undoStack.isEmpty()) && undocount < 4 ){

            System.out.println(undocount);
            int orgImg = originalFileArray[currentPosRoadRunner[0]][currentPosRoadRunner[1]];
            changeImgAt(currentPosRoadRunner[0], currentPosRoadRunner[1], orgImg);
            Integer[] toPop = undoStack.pop();

            visited.remove(toPop);

            redoStack.push(toPop);

            //Integer[] toMoveTo = undoStack.pop();
            changeImgAt(toPop[0], toPop[1], 7);
            currentPosRoadRunner[0] = toPop[0];
            currentPosRoadRunner[1] = toPop[1];
            undocount++;
            if(undocount == 3){
                undocount = 0;
            }


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

            currentPosRoadRunner[0] = currentPosRoadRunner[0] + 1;
            currentPosRoadRunner[1] = currentPosRoadRunner[1] + 1;

            visited.add(visitedCell);
            undoStack.push(visitedCell);

        }

    }

    /** Method that returns an array containing paths created by a* algorithm
     * Time Complexity 0(E) where E is number of edges traversed*/

    public ArrayList<String> astarPath(Node start, Node end){
        int rows = file.rowSize;
        int columns = file.columnSize;

        ArrayList<String> path = new ArrayList<>();
        AStar astar = new AStar(rows, columns, start, end);
        if(checkAllDirection){
            astar.allowed8Directions = true;
        }

        String message = "";
        astar.setBlocks(blocks);
        List<Node> findpath = astar.findPath();

        int StartedRow = start.getRow();
        int StartedCol = start.getCol();
        for (Node node: findpath){
            int movedRow = node.getRow();
            int movedCol = node.getCol();

            int rowDifference = StartedRow - movedRow;
            int colDifference = StartedCol - movedCol;

            if (rowDifference == 1 && colDifference == 0){
                path.add("North");
                moveUp();
                message += "North\n";
            }
            else if (rowDifference == 0 && colDifference == 1){
                path.add("West");
                moveLeft();
                message += "West\n";
            }
            else if (rowDifference == -1 && colDifference == 0){
                path.add("South");
                moveDown();
                message += "South\n";
            }
            else if (rowDifference == 0 && colDifference == -1){
                path.add("East");
                moveRight();
                message += "East\n";
            }
            else if (rowDifference == 1 && colDifference == 1){
                path.add("NorthWest");
                moveNW();
                message += "NorthWest\n";
            }
            else if (rowDifference == 1 && colDifference == -1){
                path.add("NorthEast");
                moveNE();
                message += "NorthEast\n";
            }
            else if (rowDifference == -1 && colDifference == 1){
                path.add("SouthWest");
                moveSW();
                message += "SouthWest\n";
            }
            else if (rowDifference == -1 && colDifference == -1){
                path.add("SouthEast");
                moveSE();
                message += "SouthEast\n";
            }


            StartedRow = movedRow;
            StartedCol = movedCol;
        }

        return path;
    }



    public void getscore(int number){
        HashMap<Integer, Integer> score = new HashMap<>();
        score.put(0, -1);
        score.put(2, -2);
        score.put(3, -4);
        score.put(4, -8);
        score.put(5, 1);
        score.put(6, 5);

        checkScore += score.get(number);

    }





    /** Creating the stage*/


}
