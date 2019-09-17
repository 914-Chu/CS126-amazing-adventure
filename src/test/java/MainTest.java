import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class MainTest {

    @Test
    public void deserializeNotNull() throws IOException {

        String content =  Main.URLReader(new URL("https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json"));
        Layout layout = new Gson().fromJson(content, Layout.class);
        assertNotEquals(null, layout);
    }

    @Test
    public void name() {

    }
}

