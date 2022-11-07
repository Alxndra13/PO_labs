package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void parseTest(){
        OptionsParser parser = new OptionsParser();
        ArrayList<MoveDirection> directions = new ArrayList<>(Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD));
        assertEquals(directions, parser.parse(new String[]{"f", "f", "f", "mlem", "r", "r", "f", "f", "b"}));
    }

}