package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    void run() {

        //normal test
        MoveDirection[] directions = new OptionsParser().parse
                (new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f",});
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        SimulationEngine engine = new SimulationEngine(directions, new RectangularMap(10,5), positions);

        //check the initial positions of animals
        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(2,2));
        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(3,4));

        engine.run();

        //check the final positions of animals
        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(2,0));
        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(3,4));

        //test on differently written arguments
        directions = new OptionsParser().parse
                (new String[]{"forward", "backward", "right", "left", "forward", "forward", "right", "right", "forward",
                        "forward", "forward", "forward", "forward", "forward", "forward", "forward",});
        positions = new Vector2d[]{new Vector2d(2,2), new Vector2d(3,4)};
        engine = new SimulationEngine(directions, new RectangularMap(10,5), positions);

        //check the initial positions of animals
        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(2,2));
        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(3,4));

        engine.run();

        //check the final positions of animals
        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(2,0));
        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(3,4));
    }
}