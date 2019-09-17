import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class URLConverterTest {

    private String url;

    @Before
    public void setUp() throws Exception {

        url = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";

    }

    @Test
    public void testGetJson() throws IOException {

        String content =  URLConverter.getJson(url);
        System.out.println(content);
        assertNotEquals(null, content);
    }

    @Test
    public void testInvalidURL() throws IOException{

        String wrongURL = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments";
        String content = URLConverter.getJson(wrongURL);
        System.out.println(content);
        assertEquals(null, content);
    }
}


