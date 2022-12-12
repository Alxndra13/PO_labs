package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;

    private final int delay = 500;

    private final App app;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions, App app, MapDirection startDirection) throws IllegalArgumentException{
        this.directions = directions;
        this.map = map;
        this.app = app;
        for (Vector2d pos : positions){
            Animal posAnimal = new Animal(this.map, pos, startDirection);
            this.map.place(posAnimal); //do wywalenia
        }
    }



    public void run()  {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(map.toString());
        List<Animal> animalsList = new ArrayList<Animal>(map.getAnimals().values());
        for(int i = 0; i < directions.length; i++){
            int j = i%animalsList.size();
            animalsList.get(j).move(directions[i]);
            Platform.runLater(() -> {app.drawGrid((GrassField) map);});
            try {
                Thread.sleep(delay);
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
