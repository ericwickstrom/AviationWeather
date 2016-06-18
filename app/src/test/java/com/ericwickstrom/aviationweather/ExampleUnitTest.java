package com.ericwickstrom.aviationweather;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        fail();
    }

    @Test
    public void weather_test() throws Exception {
        WeatherXmlParser wxp = new WeatherXmlParser();
        String str = wxp.parse();
        assertEquals(str, "derpderp");
    }
}