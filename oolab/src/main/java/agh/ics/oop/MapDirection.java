package agh.ics.oop;

import java.util.Map;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public String toString (){
        return switch (this){
            case NORTH -> "Północ";
            case WEST -> "Zachód";
            case EAST -> "Wschód";
            case SOUTH -> "Południe";
        };
    }

    public MapDirection next(){
        return switch(this){
            case NORTH -> EAST;
            case WEST -> NORTH;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
        };
    }

    public MapDirection previous(){
        return switch(this){
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        };
    }

    public Vector2d toUnitVector(){
        return switch(this){
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(-1,0);
        };
    }
}