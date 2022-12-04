package agh.ics.oop;

import java.util.HashMap;
import java.util.Random;

public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected HashMap<Vector2d,Animal> animals = new HashMap<>();
    protected HashMap<Vector2d,Grass> tufts = new HashMap<>();
    //    obszar poruszania się zwierząt
    protected Vector2d botLeft;
    protected Vector2d topRight;

    protected int edge; //obliczona granica pojawiania się kępek trawy

    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    //potrzebne do losowania nowych miejsc dla kępek trawy
    private final Random random = new Random();

    public MapBoundary boundary = new MapBoundary();

    public AbstractWorldMap(Vector2d topRight, Vector2d botLeft, int edge) {
        if (topRight.follows(botLeft)) {
            this.botLeft = botLeft;
            this.topRight = topRight;
        }
        this.edge = edge;
    }


    public String toString() {
        return mapVisualizer.draw(boundary.getBottomLeft(), boundary.getTopRight());
    }

    public void addAnimalToMap (Animal animal){
        animals.put(animal.getPosition(), animal); //zwierzątko do hashmapy -> observer dla zwierzątka
        animal.addObserver(this);
        boundary.addPosition(animal.getPosition());
    }

    public boolean place(Animal animal){
        if(objectAt(animal.getPosition()) instanceof Grass){//na docelowej pozycji jest trawa
            Vector2d oldPosition = ((Grass) objectAt(animal.getPosition())).getPosition();
            Vector2d newPosition = randomPosition();

            ((Grass) objectAt(animal.getPosition())).position = newPosition;
            this.positionChangedTufts(oldPosition, newPosition); //zmiana pozycji dla kępki
            boundary.positionChanged(oldPosition, newPosition);
            addAnimalToMap(animal);
            return true;
        }
        else if(this.canMoveTo(animal.getPosition())){ //jeśli nie ma tam zwierzęcia - jest pusto
            addAnimalToMap(animal);
            return true;
        }
        throw new IllegalArgumentException(animal + " " + animal.position + " is a duplicated animal");
//        return false; //nie udało się postawić zwierzątka na mapie
    }

    public boolean canMoveTo(Vector2d position) {
        //jeśli na danej pozycji jest trawa -> może wejść, ale trawie trzeba znaleźć nowe miejsce
        return (!isOccupied(position))
                && position.follows(this.botLeft)
                && position.precedes(this.topRight);
    }
    public boolean isOccupied(Vector2d position) {//czy miejsce zajęte przez jakieś zwierze
        return animals.containsKey(position);
    }

    //zwraca zwierzę na tej pozycji (lub null)
    public Object objectAt(Vector2d position) {//zwierzę lub null na danej pozycji
        return animals.get(position);
    }

    protected Vector2d generateRandomVector(int edge) {
        return new Vector2d(random.nextInt(edge + 1), random.nextInt(edge + 1));
    }

    protected Vector2d randomPosition() {
        Vector2d newRandomPosition = this.generateRandomVector(edge);
        //generujemy nowe losowe miejsce, dopóki nie będzie wolne
        while (this.objectAt(newRandomPosition) != null) {
            newRandomPosition = this.generateRandomVector(edge);
        }
        return newRandomPosition;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition,animal);
        boundary.positionChanged(oldPosition, newPosition);
    }

    public void positionChangedTufts(Vector2d oldPosition, Vector2d newPositon){
        Grass tuft = tufts.get(oldPosition);
        tufts.remove(oldPosition);
        tufts.put(newPositon,tuft);
        boundary.positionChanged(oldPosition, newPositon);
    }

    public HashMap<Vector2d,Animal> getAnimals(){
        return animals;
    }

    public HashMap<Vector2d,Grass> getTufts(){
        return tufts;
    }

}