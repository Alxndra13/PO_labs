package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    void toStringTest(){
        Animal kotek = new Animal();
        assertEquals(kotek.toString(), "N");
        kotek.move(MoveDirection.LEFT);
        assertEquals(kotek.toString(), "W");
    }

    @Test
    void isAtTest(){
        Animal kotek = new Animal(new RectangularMap(5,5));
        assertTrue(kotek.isAt(new Vector2d(2,2)));
        assertFalse(kotek.isAt(new Vector2d(-1,-1)));
        kotek.move(MoveDirection.FORWARD);
        assertTrue(kotek.isAt(new Vector2d(2,3)));
        assertFalse(kotek.isAt(new Vector2d(3,3)));
    }

    @Test
    void moveTest(){
        Animal kotek = new Animal(new RectangularMap(5,5));

        kotek.move(MoveDirection.LEFT);
        assertEquals(kotek.getDirection(), MapDirection.WEST);
        assertEquals(kotek.getPosition(), new Vector2d(2,2));

        kotek.move(MoveDirection.RIGHT);
        assertEquals(kotek.getDirection(), MapDirection.NORTH);
        assertEquals(kotek.getPosition(), new Vector2d(2,2));

        kotek.move(MoveDirection.FORWARD);
        assertEquals(kotek.getDirection(), MapDirection.NORTH);
        assertEquals(kotek.getPosition(), new Vector2d(2,3));

        kotek.move(MoveDirection.BACKWARD);
        assertEquals(kotek.getDirection(), MapDirection.NORTH);
        assertEquals(kotek.getPosition(), new Vector2d(2,2));

        for(int i=0; i<3; i++) {
            kotek.move(MoveDirection.FORWARD);
        }
        assertEquals(kotek.getPosition(), new Vector2d(2,5));
        assertEquals(kotek.getDirection(), MapDirection.NORTH);

        kotek.move(MoveDirection.LEFT);
        for(int i=0; i<3; i++){
            kotek.move(MoveDirection.FORWARD);
        }
        assertEquals(kotek.getPosition(), new Vector2d(0,5));
        assertEquals(kotek.getDirection(), MapDirection.WEST);

        for(int i=0; i<4; i++) {
            kotek.move(MoveDirection.BACKWARD);
        }
        assertEquals(kotek.getPosition(), new Vector2d(4,5));
        assertEquals(kotek.getDirection(), MapDirection.WEST);

        for(int i=0; i<3; i++){
            kotek.move(MoveDirection.RIGHT);
        }
        for(int i=0; i<4; i++) {
            kotek.move(MoveDirection.FORWARD);
        }
        assertEquals(kotek.getPosition(), new Vector2d(4,1));
        assertEquals(kotek.getDirection(), MapDirection.SOUTH);

    }
}