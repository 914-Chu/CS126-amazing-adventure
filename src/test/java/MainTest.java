import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class MainTest {

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void deserializeNotNull() throws IOException {

        String content =  URLConverter.getJson();
        Layout layout = new Gson().fromJson(content, Layout.class);
        assertNotEquals(null, layout);
    }

    @Test
    public void name() {

    }
}

