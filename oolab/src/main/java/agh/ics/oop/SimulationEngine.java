package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.directions = directions;
        this.map = map;
        for (Vector2d pos : positions){
            Animal posAnimal = new Animal(this.map, pos);
            this.map.place(posAnimal); //do wywalenia
        }
    }



    public void run() {
        System.out.println(map.toString());
        List<Animal> animalsList = new ArrayList<Animal>(map.getAnimals().values());
        for(int i = 0; i < directions.length; i++){
            int j = i%animalsList.size();
            animalsList.get(j).move(directions[i]);
            System.out.println(map.toString());
        }
    }
}
