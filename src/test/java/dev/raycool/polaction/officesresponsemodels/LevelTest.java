package dev.raycool.polaction.officesresponsemodels;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LevelTest {

    @Test
    void getLevel() {
       Level level = new Level("testLevel");
       String actual = level.getLevel();
       String expected = "testLevel";
       Assertions.assertEquals(expected, actual);
    }

    @Test
    void setLevel() {
        Level level = new Level("testLevel");
        level.setLevel("testLevelSet");
        String expected = "testLevelSet";
        String actual = level.getLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        Level level = new Level("testLevel");
        String expected = "Level: testLevel";
        String actual = level.toString();
        Assertions.assertEquals(expected, actual);
    }
}