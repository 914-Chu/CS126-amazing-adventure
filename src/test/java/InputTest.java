import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputTest {


    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testValidateURL() {

        String goodURL = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        String badURL = "https:/grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        Input input = new Input();
        assertTrue(input.validateURL(goodURL));
        assertFalse(input.validateURL(badURL));
    }
}
