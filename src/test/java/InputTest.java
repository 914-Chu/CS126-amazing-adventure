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

        String goodURL = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        String badURL = "https://forums";
        assertTrue(input.isValidURL(goodURL));
        assertFalse(input.isValidURL(badURL));
    }

    @Test
    public void testIsValidPath() {

        String goodPath = "test_resources\\content";
        String badPath = "src\test\\test_resources";
        assertTrue(input.isValidPath(goodPath));
        assertFalse(input.isValidPath(badPath));
    }
}
