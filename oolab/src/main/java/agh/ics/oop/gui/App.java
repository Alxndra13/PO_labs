package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application{

    private AbstractWorldMap map;

    @Override
    public void start(Stage primaryStage){
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
//            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,2), new Vector2d(3,4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            drawGrid(primaryStage); //wywolanie rysowania mapy
        }
        catch (IllegalArgumentException exception){
            System.out.println(exception);
        }

    }

    public void drawGrid(Stage primaryStage){

        int xMin = map.boundary.getBottomLeft().x;
        int yMin = map.boundary.getBottomLeft().y;
        int xMax = map.boundary.getTopRight().x;
        int yMax = map.boundary.getTopRight().y;

        int width = 30;
        int height = 30;

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

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
                    Object object = map.objectAt(position);
                    label = new Label(object.toString());
                    grid.add(label, position.x-xMin+1, yMax-position.y+1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
