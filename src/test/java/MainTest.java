import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MainTest {

    private String url;
    private Gson gson;
    @Before
    public void setUp() throws Exception {

        url = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        gson = new Gson();
    }

    @Test
    public void deserializeNotNull() throws IOException, com.google.gson.JsonParseException {

        String content =  URLConverter.getJson(url);
        Layout layout = gson.fromJson(content, Layout.class);
        assertNotEquals(null, layout);
    }

    @Test
    public void name() {

    }
}

