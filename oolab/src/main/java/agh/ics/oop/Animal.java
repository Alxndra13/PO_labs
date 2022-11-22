package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;

    public Animal(){
        super(new Vector2d(2,2));
        this.map = new RectangularMap(4,4);
    }

    public Animal(IWorldMap map){
        super(new Vector2d(2,2));
        this.map = map;

    }

    public Animal(IWorldMap map, Vector2d initialPosition){
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

    public void move(MoveDirection direction){
        Vector2d tempPosition;
        switch (direction){
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> {
                tempPosition = this.position.add(this.direction.toUnitVector());
                if (map instanceof GrassField && updatePosition(tempPosition)) break;
                else if (map.canMoveTo(tempPosition)) this.position = tempPosition;
            }
            case BACKWARD -> {
                tempPosition = this.position.subtract(this.direction.toUnitVector());
                if (map instanceof GrassField && updatePosition(tempPosition)) break;
                else if (map.canMoveTo(tempPosition)) this.position = tempPosition;
            }
        }
    }

    public boolean updatePosition(Vector2d tempPosition){
        GrassField grassMap = (GrassField) this.map; //obiekt typu grassField
        if(grassMap.objectAt(tempPosition) instanceof Grass){ //jeśli w danym miejscu jest kępka trawy
            //losujemy kępce trawy nowe miejsce
            ((Grass) grassMap.objectAt(tempPosition)).position = grassMap.randomPosition();
            this.position = tempPosition; //zwierzę wchodzi na docelową pozycję
            return true;
        }
        return false;
    }
}
