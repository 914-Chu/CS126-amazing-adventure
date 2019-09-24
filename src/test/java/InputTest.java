import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class InputTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testProcessInputIsNull() throws IOException {

        String invalidInput = "ugld8sjfole;;";
        String nothing = "";
        assertNull(Input.getJson(invalidInput));
        assertNull(Input.getJson(nothing));
    }

    @Test
    public void testIsValidURL() {

        String validURL = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        String invalidFormURL = "courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        String invalidConnectionURL = "https://forums";
        assertTrue(Input.isValidURL(validURL));
        assertFalse(Input.isValidURL(invalidFormURL));
        assertFalse(Input.isValidURL(invalidConnectionURL));
    }

    @Test
    public void testIsValidPath() {

        String validFile = "content";
        String invalidFile = "java";
        assertTrue(Input.isValidFile(validFile));
        assertFalse(Input.isValidFile(invalidFile));
    }

    //Unsolved: Different schema
    @Test
    public void testIsValidJson() {

        String validJson = "{\"status\": \"UP\"}";
        String invalidJson = "\"status\": \"UP\"}";
        assertTrue(Input.isValidJson(validJson));
        assertFalse(Input.isValidJson(invalidJson));
    }

}
