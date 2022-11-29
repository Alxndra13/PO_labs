package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.abs;

class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) { //always correct parameters
        super(new Vector2d(width, height), new Vector2d(0, 0), -1);
    }

    public HashMap<Vector2d,Grass> getTufts(){
        return new HashMap<>();
    }
}