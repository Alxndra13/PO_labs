package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static Direction stringToEnum(String[] args) {
        Direction direction = Direction.NONE;
        for (int i = 0; i < args.length; i++) {
            String argument = args[i];
            switch (argument) {
                case "f":
                    direction = Direction.valueOf("f");
                    break;
                case "b":
                    direction = Direction.valueOf("b");
                    break;
                case "r":
                    direction = Direction.valueOf("r");
                    break;
                case "l":
                    direction = Direction.valueOf("l");
                    break;
            }
        }
        return direction;
    }

    public static String[] names() {
        return Arrays.stream(Direction.values()).map(Enum::name).toArray(String[]::new);
    }

    public static void run(Direction direction) {
        String[] args = names();
        for (int i = 0; i < args.length; i++) {
            String message = switch (args[i]) {
                case "f" -> "Zwierzak idzie do przodu";
                case "b" -> "Zwierzak idzie do tylu";
                case "r" -> "Zwierzak skreca w prawo";
                case "l" -> "Zwierzak skreca w lewo";
                default -> "";
            };
            System.out.println(message);
        }
    }

    public static void main (String[]args){
        System.out.println("Start");
        Direction direction = stringToEnum(args);
        run(direction);
        System.out.println("Stop");
    }
}
