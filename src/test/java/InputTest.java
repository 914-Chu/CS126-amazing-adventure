import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputTest {

    Input input;
    @Before
    public void setUp() throws Exception {

        input = new Input();
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

    @Test
    public void testIsValidJson() {

        String validJson = "{\"status\": \"UP\"}";
        String invalidJson = "ldakjpole";
        assertTrue(input.isValidJson(validJson));
        assertFalse(input.isValidJson(invalidJson));
    }
}
