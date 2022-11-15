package agh.ics.oop;

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

    //f b r l f f r r f f f f f f f f
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10,5);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }
}
