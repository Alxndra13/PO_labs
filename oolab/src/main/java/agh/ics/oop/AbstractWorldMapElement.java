package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position;
    protected List<IPositionChangeObserver> observers = new ArrayList<>();

    public AbstractWorldMapElement(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return position;
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    abstract public String getImageSrc();

}
