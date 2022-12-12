//package agh.ics.oop;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class RectangularMapTest {
//    String[] args ={"f", "b", "f", "b"};
//    MoveDirection[] directions = new OptionsParser().parse(args);
//    IWorldMap map = new RectangularMap(10,10);
//    Vector2d[] positions = {new Vector2d(2,1), new Vector2d(2,4)};
//    IEngine engine = new SimulationEngine(directions, map, positions);
//    //dane dobrane pod kolizję zwierząt
//
//    @Test
//    void placeTest(){
//        engine.run();
//        System.out.println(map.getAnimals());
//
//        //czy można postawić na puste pozycje z których zwierzęta już odeszły
//        assertTrue(map.place(new Animal(map, new Vector2d(2,4))));
//        assertTrue(map.place(new Animal(map, new Vector2d(2,1))));
//
//        //wyjątki - czy próba postawienia zwierzęcia na zajętej pozycji zwróci wyjątek
//        Assertions.assertThrows(IllegalArgumentException.class, ()->map.place(new Animal(map, new Vector2d(2,3))));
//    }
//
//    @Test
//    void canMoveToTest(){
//        engine.run();
//        assertFalse(map.canMoveTo(new Vector2d(2,2)));
//        assertFalse(map.canMoveTo(new Vector2d(2,3)));
//        assertTrue(map.canMoveTo(new Vector2d(2,1)));
//        assertTrue(map.canMoveTo(new Vector2d(2,4)));
//    }
//
//    @Test
//    void isOccupiedTest() {
//        engine.run();
//        assertTrue(map.isOccupied(new Vector2d(2,2)));
//        assertTrue(map.isOccupied(new Vector2d(2,3)));
//        assertFalse(map.isOccupied(new Vector2d(2,1)));
//        assertFalse(map.isOccupied(new Vector2d(2,4)));
//    }
//
//    @Test
//    void objectAtTest() {
//        engine.run();
//        HashMap<Vector2d,Animal> animals = map.getAnimals();
//        assertEquals(map.objectAt(new Vector2d(2,2)), animals.get(new Vector2d(2,2)));
//        assertEquals(map.objectAt(new Vector2d(2,3)), animals.get(new Vector2d(2,3)));
//        assertEquals(map.objectAt(new Vector2d(2,4)), null);
//        assertNotEquals(map.objectAt(new Vector2d(2,2)), new Vector2d(2,3));
//    }
//}