import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class InputTest {

    Input input;
    @Before
    public void setUp() {

        input = new Input();
    }

    @Test
    public void testProcessInputIsNull() throws IOException {

        String invalidInput = "\\\\em;ladsjfole;;";
        String nothing = "";
        assertNull(input.processInput(invalidInput));
        assertNull(input.processInput(nothing));
    }

    @Test
    public void testIsValidURL() {

        String validURL = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        String invalidFormURL = "courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        String invalidConnectionURL = "https://forums";
        assertTrue(input.isValidURL(validURL));
        assertFalse(input.isValidURL(invalidFormURL));
        assertFalse(input.isValidURL(invalidConnectionURL));
    }

    @Test
    public void testIsValidPath() {

        String validPath = "test_resources\\content";
        String invalidPath = "src\test\\test_resources";
        assertTrue(input.isValidPath(validPath));
        assertFalse(input.isValidPath(invalidPath));
    }

    //Unsolved: Different schema
    @Test
    public void testIsValidJson() {

        String validJson = "{\"status\": \"UP\"}";
        String invalidJson = "\"status\": \"UP\"}";
        assertTrue(input.isValidJson(validJson));
        assertFalse(input.isValidJson(invalidJson));
    }
}
