package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void run(String[] args){
        System.out.println("zwierzak idzie do przodu");
        System.out.print(args[0]);
        for(int i=1; i<args.length; i++){
            System.out.print(", ");
            System.out.print(args[i]);
        }
        System.out.println(" ");



        for (int i=0; i<args.length; i++){
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

    public static void main(String[] args){
        System.out.println("Start");

        run(args);
        System.out.println("Stop");
    }
}
