import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class RoomTest {

    private String url;
    private String content;
    private Gson gson;
    private Layout layout;
    private List<Room> roomList;
    Random rand = new Random();

    @Before
    public void setUp() throws Exception {

        url = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        gson = new Gson();
        content =  URLConverter.getJson(url);
        layout = gson.fromJson(content, Layout.class);
        roomList = layout.getRoom();
    }


}
