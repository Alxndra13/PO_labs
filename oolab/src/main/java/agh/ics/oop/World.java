package agh.ics.oop;

import java.util.ArrayList;

public class World {
    public static Direction[] stringToEnum(String[] args) {
        Direction[] direction = new Direction[args.length];
        int i = 0;
        for (String argument : args) {
            switch (argument) {
                case "f":
                    direction[i] = Direction.F;
                    break;
                case "b":
                    direction[i] = Direction.B;
                    break;
                case "r":
                    direction[i] = Direction.R;
                    break;
                case "l":
                    direction[i] = Direction.L;
                    break;
                default:
                    direction[i] = Direction.NONE;
            }
            i++;
        }
        return direction;
    }


    public static void run(Direction[] direction) {
        for (Direction element : direction) {
            switch (element) {
                case F -> System.out.println("Zwierzak idzie do przodu");
                case B -> System.out.println("Zwierzak idzie do tylu");
                case R -> System.out.println("Zwierzak skreca w prawo");
                case L -> System.out.println("Zwierzak skreca w lewo");
            }
        }
    }

    public static void main(String[] args) {
        Animal zwierzaczek = new Animal();
        System.out.println(zwierzaczek.toString());
        OptionsParser parser = new OptionsParser();
        ArrayList<MoveDirection> directions = parser.parse(args);
        for (MoveDirection element : directions){
            zwierzaczek.move(element);
            System.out.println(zwierzaczek.toString());
        }
    }
}
