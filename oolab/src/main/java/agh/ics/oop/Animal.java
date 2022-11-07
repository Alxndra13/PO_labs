package agh.ics.oop;

import java.util.Map;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return "position: " + this.position.toString() + ", direction: " + this.direction;
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }
    public void move(MoveDirection direction){ //pozbyc sie this gdzie useless
        Vector2d tempPosition = new Vector2d(1,1);
        switch (direction){
            case RIGHT -> {this.direction = this.direction.next(); return;}
            case LEFT -> {this.direction = this.direction.previous(); return;}
            case FORWARD -> tempPosition = this.position.add(this.direction.toUnitVector());
            case BACKWARD -> tempPosition = this.position.subtract(this.direction.toUnitVector());
        }

        if (tempPosition.x > 4 || tempPosition.x < 0 || tempPosition.y >4 || tempPosition.y<0) return;
        else this.position = tempPosition;
    }
}
