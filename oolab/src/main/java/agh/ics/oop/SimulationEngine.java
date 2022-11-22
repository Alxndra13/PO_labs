package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final List<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.directions = directions;
        this.map = map;
        for (Vector2d pos : positions){
            Animal posAnimal = new Animal(map, pos);
            this.animals.add(posAnimal);
            map.place(posAnimal);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public void run() {
        System.out.println(map.toString());
        for(int i = 0; i< directions.length; i++){
            int j = i%animals.size();
            animals.get(j).move(directions[i]);
            System.out.println(map.toString());
            }
    }
}
