import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

import java.io.IOException;

public class MainTest {

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

    @Test
    public void deserializeNotNull() throws IOException, com.google.gson.JsonParseException {

        assertNotEquals(null, layout);
    }

    @Test
    public void testCheckInput() {

        String beginWithoutGo = "akdj;le go akj";
        assertEquals(false, Main.checkInput(beginWithoutGo));
    }

    @Test
    public void testCheckDirection() throws Exception {

        int roomSize = roomList.size();
        int randomIndex = rand.nextInt(roomSize);
        Room room = roomList.get(randomIndex);

        int directionSize = room.getDirectionsList().size();
        int randomIndex1 = rand.nextInt(directionSize);
        String direction = room.getDirectionsList().get(randomIndex1).getDirectionName();

        assertEquals(true, Main.checkDirection(direction, room));
        assertEquals(false, Main.checkDirection("somewhere", room));
    }

    @Test
    public void testCheckEnd() {

        String expect = "Siebel1314";
        String unexpect = "SiebelNorthHallway";
        String endingRoom = layout.getEndingRoom();
        
        assertEquals(true, Main.checkEnd(expect, endingRoom));
        assertEquals(false, Main.checkEnd(unexpect, endingRoom));
    }
}

