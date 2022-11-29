package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    void runTest() {
        //normal test
        MoveDirection[] directions = new OptionsParser().parse
                (new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f",});
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        IWorldMap map = new RectangularMap(20,20);
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        //check the initial positions and directions of animals
//        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(2,2));
//        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(3,4));
//        assertEquals(engine.getAnimals().get(0).getDirection(), MapDirection.NORTH);
//        assertEquals(engine.getAnimals().get(1).getDirection(), MapDirection.NORTH);
//
//        engine.run();
//
//        //check the final positions and directions of animals
//        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(2,0));
//        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(3,7));
//        assertEquals(engine.getAnimals().get(0).getDirection(), MapDirection.SOUTH);
//        assertEquals(engine.getAnimals().get(1).getDirection(), MapDirection.NORTH);
//
//        //test on differently written arguments
//        directions = new OptionsParser().parse
//                (new String[]{"forward", "backward", "right", "left", "forward", "forward", "right", "right", "forward",
//                        "forward", "forward", "forward", "forward", "forward", "forward", "forward",});
//        positions = new Vector2d[]{new Vector2d(2,2), new Vector2d(3,4)};
//        engine = new SimulationEngine(directions, map, positions);
//
//        //check the initial positions and directions of animals
//        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(2,2));
//        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(3,4));
//        assertEquals(engine.getAnimals().get(0).getDirection(), MapDirection.NORTH);
//        assertEquals(engine.getAnimals().get(1).getDirection(), MapDirection.NORTH);
//
//        engine.run();
//
//        //check the final positions and directions of animals
//        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(2,1));
//        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(3,6));
//        assertEquals(engine.getAnimals().get(0).getDirection(), MapDirection.SOUTH);
//        assertEquals(engine.getAnimals().get(1).getDirection(), MapDirection.NORTH);
    }
}