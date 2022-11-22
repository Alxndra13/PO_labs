package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;

public class AbstractWorldMap implements IWorldMap {
    protected ArrayList<Animal> animals = new ArrayList<>();
    protected ArrayList<Grass> tufts = new ArrayList<>();


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
        if (edge > 0) this.edge = edge;
    }

    public String toString() {
        this.changeEdges();
        return mapVisualizer.draw(this.botLeftEdge, this.topRightEdge);

    }

    public void changeEdges() {

        //wartości startowe
        this.botLeftEdge = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.topRightEdge = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Animal animal : animals) { //najpierw szuka dla zwierząt
            if (animal.getPosition().x < this.botLeftEdge.x) this.botLeftEdge.x = animal.getPosition().x;
            if (animal.getPosition().y < this.botLeftEdge.y) this.botLeftEdge.y = animal.getPosition().y;
            if (animal.getPosition().x > this.topRightEdge.x) this.topRightEdge.x = animal.getPosition().x;
            if (animal.getPosition().y > this.topRightEdge.y) this.topRightEdge.y = animal.getPosition().y;
        }

        for (Grass tuft : tufts) { //teraz szuka dla kępek trawy
            if (tuft.getPosition().x < this.botLeftEdge.x) this.botLeftEdge.x = tuft.getPosition().x;
            if (tuft.getPosition().y < this.botLeftEdge.y) this.botLeftEdge.y = tuft.getPosition().y;
            if (tuft.getPosition().x > this.topRightEdge.x) this.topRightEdge.x = tuft.getPosition().x;
            if (tuft.getPosition().y > this.topRightEdge.y) this.topRightEdge.y = tuft.getPosition().y;
        }
    }

    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position))
                && position.follows(this.botLeft)
                && position.precedes(this.topRight);
    }

    public boolean place(Animal animal) {
        if (objectAt(animal.getPosition()) instanceof Grass) { //w tym miejscu jest trawa
            ((Grass) objectAt(animal.getPosition())).position = randomPosition(); //nowa pozycja dla trawy
            animals.add(animal); //zwierzę wchodzi na miejsce trawy która się przeniosła
            return true;
        }
        if (this.canMoveTo(animal.getPosition())) { //nic tam nie ma, wolne pole
            animals.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {//czy miejsce zajęte przez jakieś zwierze
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position) {//zwierzę lub null na danej pozycji
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) return animal;
        }
        return null;
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
}