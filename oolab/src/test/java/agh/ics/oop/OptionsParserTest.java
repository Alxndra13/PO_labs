package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void parseTest(){

        //test on correct arguments
        String[] args1 = {"f", "f", "f", "r", "r", "f", "f", "b"};
        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD};
        assertTrue(Arrays.equals(directions, parser.parse(args1)));

        assertArrayEquals(directions, parser.parse(args1));

        //test with uncorrect arguments
        String[] args2 = new String[]{"f", "f", "f", "mlem", "r", "r", "f", "f", "b"};
        Assertions.assertThrows(IllegalArgumentException.class, ()-> new OptionsParser().parse(args2));
    }

}