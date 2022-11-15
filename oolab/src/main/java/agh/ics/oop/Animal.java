package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;
    private IWorldMap map;

    public Animal(){
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map){
        this();
        this.map = map;

    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this(map);
        this.position = initialPosition;
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return this.direction.toString();
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }
    public void move(MoveDirection direction){
        Vector2d tempPosition = new Vector2d(1,1);
        switch (direction){
            case RIGHT -> {this.direction = this.direction.next(); return;}
            case LEFT -> {this.direction = this.direction.previous(); return;}
            case FORWARD -> tempPosition = this.position.add(this.direction.toUnitVector());
            case BACKWARD -> tempPosition = this.position.subtract(this.direction.toUnitVector());
        }
        if (map.canMoveTo(tempPosition)) this.position = tempPosition;
    }
}
