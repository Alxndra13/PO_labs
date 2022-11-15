package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

class RectangularMap implements IWorldMap {
    private final int width, height;
    private final int minX, minY;
    private final List<Animal> animals;
    private final MapVisualizer mapVisualizer;

    public RectangularMap(int width, int height){ //always correct parameters
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<>();
        this.minX = 0;
        this.minY = 0;
        this.mapVisualizer = new MapVisualizer(this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position) &&
                position.follows(new Vector2d(minX, minY)) &&
                position.precedes(new Vector2d(width-1, height-1));
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals){
            if (animal.getPosition().equals(position)) return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals){
            if (animal.getPosition().equals(position)) return animal;
        }
        return null;
    }

    @Override
    public String toString(){
        return mapVisualizer.draw(new Vector2d(minX, minY), new Vector2d(width-1, height-1));
    }
}
