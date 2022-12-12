package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application{

    private AbstractWorldMap map;
    private MapDirection startDirection = MapDirection.NORTH;
    private GridPane grid;

    //f b r l f f r r f f f f f f f f

    @Override
    public void start(Stage primaryStage){
        TextField text = new TextField(); //pole tekstowe na sekwencje ruchów
        text.setMaxWidth(500);
        grid = new GridPane();
        drawGrid(new GrassField(10)); //startowa mapa
        VBox vBox = new VBox(grid, text, createStartButton(text), createDirectionButton());

        Scene scene = new Scene(vBox, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public Button createStartButton(TextField text){
        Button startButton = new Button("START");
        startButton.setOnAction((action) ->{ //na klikniecie przycisku
            String args = text.getText();
            try {
                MoveDirection[] directions = new OptionsParser().parse(args.split(" "));
                Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
                IEngine engine = new SimulationEngine(directions, map, positions, this, startDirection);
                Thread thread = new Thread(engine::run); //thread na metodzie engine.run
                thread.start();
            } catch (IllegalArgumentException e) //obsługa błędnych argumentów i zduplikowanych zwierząt
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Exception alert");
                alert.setHeaderText(e.getMessage());
                alert.setContentText("You need to correct it!");
                alert.showAndWait();
            }
        });
        return startButton;
    }

    public Button createDirectionButton(){ //przycik zmiany startowej orientacji
        Button dirButton = new Button(startDirection.toString());
        dirButton.setOnAction((action) -> {
            startDirection = startDirection.next();
            dirButton.setText(startDirection.toString());
        });
        return dirButton;
    }


    public void drawGrid(GrassField newMap){

        //wyczyszczenie
        grid.setGridLinesVisible(false);
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);
        map = newMap;

        //granice mapy
        int xMin = map.boundary.getBottomLeft().x;
        int yMin = map.boundary.getBottomLeft().y;
        int xMax = map.boundary.getTopRight().x;
        int yMax = map.boundary.getTopRight().y;

        //rozmiar okienek
        int width = 40;
        int height = 40;

        //lewy górny róg
        Label label = new Label("x/y");
        grid.getColumnConstraints().add(new ColumnConstraints(width));
        grid.getRowConstraints().add(new RowConstraints(height));
        grid.add(label,0,0);
        GridPane.setHalignment(label, HPos.CENTER);

        // os X
        for(int i=xMin; i<=xMax; i++){
            label = new Label(Integer.toString(i));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(width));
            grid.add(label, i-xMin+1, 0);
        }

        //os Y
        for (int i=yMax; i >= yMin; i--){
            label = new Label((Integer.toString(i)));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(height));

            grid.add(label, 0, yMax-i+1);
        }

//        wypełnienie siatki zwierzętami i kępkami
        for (int x=xMin; x <= xMax; x++){
            for (int y=yMax; y>=yMin; y--){
                Vector2d position = new Vector2d(x,y);
                if (map.objectAt(position) != null){
                    GuiElementBox vBox = new GuiElementBox((IMapElement) map.objectAt(position));
                    label = new Label();
                    GridPane.setHalignment(label, HPos.CENTER);
                    grid.add(vBox.vBox, position.x-xMin+1, yMax-position.y+1);
                }
            }
        }
    }
}
