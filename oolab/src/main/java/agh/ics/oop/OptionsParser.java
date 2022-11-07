package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {
    public ArrayList<MoveDirection> parse(String[] args){
        ArrayList<MoveDirection> directions = new ArrayList<>();
        for (String element : args){
            switch (element){
                case "f", "forward" -> directions.add(MoveDirection.FORWARD);
                case "b", "backward" -> directions.add(MoveDirection.BACKWARD);
                case "l", "left" -> directions.add(MoveDirection.LEFT);
                case "r", "right" -> directions.add(MoveDirection.RIGHT);
            }
        }
        return directions;
    }
}
