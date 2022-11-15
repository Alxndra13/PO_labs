package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void parseTest(){
        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD};
        assertTrue(Arrays.equals(directions, parser.parse(new String[]{"f", "f", "f", "mlem", "r", "r", "f", "f", "b"})));

        assertArrayEquals(directions, parser.parse(new String[]{"f", "f", "f", "mlem", "r", "r", "f", "f", "b"}));
    }

}