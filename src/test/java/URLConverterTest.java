import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class URLConverterTest {

    private String url;

    @Before
    public void setUp() throws Exception {

        url = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";

    }

    @Test
    public void deserializeNotNull() throws IOException {

        String content =  URLConverter.getJson(url);
        System.out.println();
        assertNotEquals(null, content);
    }

    
}


