//package agh.ics.oop;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.Random;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class GrassFieldTest {
//    Random random = new Random();
//    //f b r l f f r r f f f f f f f f
//    String[] arguments ={"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
//    MoveDirection[] directions = new OptionsParser().parse(arguments);
//    Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
//    IWorldMap map = new GrassField(20);
//    IEngine engine = new SimulationEngine(directions, map, positions);
//
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
//        Assertions.assertThrows(IllegalArgumentException.class, ()->map.place(new Animal(map, new Vector2d(2,-1))));
//        Assertions.assertThrows(IllegalArgumentException.class, ()->map.place(new Animal(map, new Vector2d(3,7))));
//    }
//    @Test
//    void isOccupiedTest(){
//        engine.run();
//        assertTrue(map.isOccupied(new Vector2d(3,7)));
//        assertTrue(map.isOccupied(new Vector2d(2,-1)));
//
//        //pola poza obszarem trawy
//        assertFalse(map.isOccupied(new Vector2d(25,25)));
//        assertFalse(map.isOccupied(new Vector2d(-2,-2)));
//    }
//
//    @Test
//    void objectAtTest(){
//        engine.run();
//
//        //czy zwierzęta są w odpowiednim miejscu
//        assertTrue(map.objectAt(new Vector2d(3,7)) instanceof Animal);
//        assertTrue(map.objectAt(new Vector2d(2,-1)) instanceof Animal);
//
//        //gdzie indziej może być trawa, ale nie zwierzę:
//        assertFalse(map.objectAt(new Vector2d(1,1)) instanceof Animal);
//    }
//}