package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    TreeSet<Vector2d> xAxis = new TreeSet<>(Comparator.comparingInt(vector -> vector.x));
    TreeSet<Vector2d> yAxis = new TreeSet<>(Comparator.comparingInt(vector -> vector.y));

    public void addPosition(Vector2d position){
        xAxis.add(position);
        yAxis.add(position);
    }


    public void removePosition(Vector2d position){
        xAxis.remove(position);
        yAxis.remove(position);
    }

    public Vector2d getBottomLeft(){
        return new Vector2d(xAxis.first().x, yAxis.first().y);
    }

    public Vector2d getTopRight(){
        return new Vector2d(xAxis.last().x, yAxis.last().y);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removePosition(oldPosition);
        addPosition(newPosition);
    }
}
