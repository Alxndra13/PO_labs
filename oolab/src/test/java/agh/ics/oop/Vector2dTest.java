package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    void equalsTest(){
        Vector2d a = new Vector2d(0,0);
        Vector2d b = new Vector2d(0,0);
        Vector2d c = new Vector2d(0,1);
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
        assertTrue(a.equals(a));
    }

    @Test
    void toStringTest(){
        Vector2d test = new Vector2d(0,0);
        assertEquals(test.toString(), "(" + test.x + "," + test.y + ")");
    }

    @Test
    void precedesTest(){
        Vector2d a = new Vector2d(0,0);
        Vector2d b = new Vector2d(1,1);
        Vector2d c = new Vector2d(1,0);
        assertTrue(a.precedes(a));
        assertTrue(a.precedes(b));
        assertTrue(a.precedes(c));
        assertFalse(c.precedes(a));
        assertFalse(b.precedes(a));
    }

    @Test
    void followsTest(){
        Vector2d a = new Vector2d(0,0);
        Vector2d b = new Vector2d(1,1);
        Vector2d c = new Vector2d(1,0);
        assertTrue(a.follows(a));
        assertFalse(a.follows(b));
        assertFalse(a.follows(c));
        assertTrue(c.follows(a));
        assertTrue(b.follows(a));
    }

    @Test
    void upperRightTest(){
        Vector2d a = new Vector2d(7,8);
        Vector2d b = new Vector2d(-1,3);
        assertEquals(a.upperRight(b), a);
        assertEquals(b.upperRight(a), a);
    }

    @Test
    void lowerLeftTest(){
        Vector2d a = new Vector2d(7,8);
        Vector2d b = new Vector2d(-1,3);
        assertEquals(a.lowerLeft(b), b);
        assertEquals(b.lowerLeft(a), b);
    }

    @Test
    void addTest(){
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(2,2);
        assertEquals(a.add(b), new Vector2d(a.x+b.x, a.y+b.y));
    }

    @Test
    void substractTest(){
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(2,2);
        assertEquals(a.subtract(b), new Vector2d(a.x-b.x, a.y-b.y));
    }

    @Test
    void oppositeTest(){
        Vector2d test = new Vector2d(1,1);
        assertEquals(test.opposite(), new Vector2d(-test.x, -test.y));
    }

}

