import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class MainTest {

    private Layout layout;
    private List<Room> roomList;
    private Random rand = new Random();
    private Input input;

    @Before
    public void setUp() throws Exception {

        String json = Input.getJson("content");
        layout = new Gson().fromJson(json, Layout.class);
        layout.setCurrentRoom(layout.getStartingRoom());
        roomList = layout.getRoomList();
    }

    @Test
    public void deserializeNotNull() throws com.google.gson.JsonParseException {

        assertNotEquals(null, layout);
    }

    @Test
    public void testStartWithGo() {

        assertTrue(Main.startWithGo("go EaST"));
        assertTrue(Main.startWithGo("Go wesT"));
        assertFalse(Main.startWithGo("akdj;le go akj"));
        assertFalse(Main.startWithGo("gopher"));
    }

    @Test
    public void testCheckEnd() {

        String isEnd = "Siebel1314";
        String notEnd = "SiebelNorthHallway";
        String endingRoom = layout.getEndingRoom();

        assertTrue(Main.checkEnd(isEnd, endingRoom));
        assertFalse(Main.checkEnd(notEnd, endingRoom));
    }

    @Test
    public void testToLeave() {

        String leave1 = "QuiT";
        String leave2 = "EXit";

        int roomSize = roomList.size();
        int randomIndex = rand.nextInt(roomSize);
        String stay = roomList.get(randomIndex).getRoomName();

        assertTrue(Main.toLeave(leave1));
        assertTrue(Main.toLeave(leave2));
        assertFalse(Main.toLeave(stay));
    }

    @Test
    public void testInvalidKeyList() {

        List<String> item = new ArrayList<>(Arrays.asList("pencil", "eraser", "pen"));
        assertTrue(Main.inValidKeyList("PeNciL",item));
        assertFalse(Main.inValidKeyList("rULer", item));
    }
}

