package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected HashMap<Vector2d,Animal> animals = new HashMap<>();
    protected HashMap<Vector2d,Grass> tufts = new HashMap<>();
    //    obszar poruszania się zwierząt
    protected Vector2d botLeft;
    protected Vector2d topRight;

    // obszar wyświetlania mapy
    protected Vector2d botLeftEdge;
    protected Vector2d topRightEdge;

    protected int edge; //obliczona granica pojawiania się kępek trawy

    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    //potrzebne do losowania nowych miejsc dla kępek trawy
    private final Random random = new Random();

    public AbstractWorldMap(Vector2d topRight, Vector2d botLeft, int edge) {
        if (topRight.follows(botLeft)) {
            this.botLeft = botLeft;
            this.topRight = topRight;
        }
        this.edge = edge;
    }


    public String toString() {
        this.changeEdges();
        return mapVisualizer.draw(this.botLeftEdge, this.topRightEdge);
    }

    public void changeEdges() {
        //wartości startowe
        this.botLeftEdge = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.topRightEdge = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        List<AbstractWorldMapElement> animalsList = new ArrayList<>(animals.values());
        lookThroughList(animalsList);
        List<AbstractWorldMapElement> tuftsList = new ArrayList<>(tufts.values());
        lookThroughList(tuftsList);
    }

    void lookThroughList (List <AbstractWorldMapElement> objectsList){
        for (AbstractWorldMapElement object : objectsList) {
            if (object.getPosition().x < this.botLeftEdge.x) this.botLeftEdge.x = object.getPosition().x;
            if (object.getPosition().y < this.botLeftEdge.y) this.botLeftEdge.y = object.getPosition().y;
            if (object.getPosition().x > this.topRightEdge.x) this.topRightEdge.x = object.getPosition().x;
            if (object.getPosition().y > this.topRightEdge.y) this.topRightEdge.y = object.getPosition().y;
        }
    }

    public boolean place(Animal animal){
        if(objectAt(animal.position) instanceof Grass){//na docelowej pozycji jest trawa
            Vector2d oldPosition = ((Grass) objectAt(animal.getPosition())).getPosition();
            Vector2d newPosition = randomPosition();

            ((Grass) objectAt(animal.getPosition())).position = newPosition;
            this.positionChangedTufts(oldPosition, newPosition); //zmiana pozycji dla kępki

            animals.put(animal.position, animal); //zwierzątko do hashmapy -> observer dla zwierzątka
            animal.addObserver(this);
            return true;
        }
        if(this.canMoveTo(animal.getPosition())){ //jeśli nie ma tam zwierzęcia - jest pusto
            animals.put(animal.position,animal); //zwierzątko do hashmapy
            animal.addObserver(this);
            return true;
        }
        return false; //nie udało się postawić zwierzątka na mapie
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
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition,animal);
    }

    public void positionChangedTufts(Vector2d oldPosition, Vector2d newPositon){
        Grass tuft = this.tufts.get(oldPosition);
        this.tufts.remove(oldPosition);
        this.tufts.put(newPositon,tuft);
    }

    public HashMap<Vector2d,Animal> getAnimals(){
        return animals;
    }

    public HashMap<Vector2d,Grass> getTufts(){
        return tufts;
    }

}