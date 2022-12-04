package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection direction = MapDirection.NORTH;
    private final IWorldMap map;


    public Animal(){
        super(new Vector2d(2,2));
        this.map = new RectangularMap(4,4);
    }

    public Animal(IWorldMap map){
        super(new Vector2d(2,2));
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        super(initialPosition);
        this.map = map;
    }

    public MapDirection getDirection() {
        return direction;
    }

    @Override
    public String toString(){
        return this.getDirection().toString();
    }

    public void move(MoveDirection dir){
        switch (dir){
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> {
                Vector2d tempPosition = this.position.add(direction.toUnitVector());
                moveAnimalForwardBackward(tempPosition);
                }
            case BACKWARD -> {
                Vector2d tempPosition = this.position.subtract(this.direction.toUnitVector());
                moveAnimalForwardBackward(tempPosition);
            }
        }
    }

    public void moveAnimalForwardBackward(Vector2d tempPosition){
        if (map instanceof GrassField && updatePosition(tempPosition)) return;
        else if (map.canMoveTo(tempPosition)) { //skoro nie było trawy to czy można tam wejść - pusto
            positionChanged(position,tempPosition);
            this.position = tempPosition;
        }
    }

    public boolean updatePosition(Vector2d tempPosition){
        GrassField grassMap = (GrassField) this.map; //obiekt typu grassField

        //jeśli w danym miejscu jest kępka
        if(grassMap.objectAt(tempPosition) instanceof Grass){
            //losujemy kępce trawy nowe miejsce
            Vector2d oldPosition = ((Grass) grassMap.objectAt(tempPosition)).getPosition();
            Vector2d newPosition = grassMap.randomPosition();

            ((Grass) grassMap.objectAt(tempPosition)).position = newPosition;
            grassMap.positionChangedTufts(oldPosition, newPosition);

            grassMap.positionChanged(position,tempPosition);
            this.position = tempPosition;
            return true;
        }
        else return false; //w tym miejscu nie ma trawy
    }

    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
