import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class MainTest {

    private Layout layout;
    private List<Room> roomList;
    private Random rand = new Random();
    private Input input;

    @Before
    public void setUp() throws Exception {

        input = new Input();
        String json = input.processInput("content");
        layout = new Gson().fromJson(json, Layout.class);
        layout.setCurrentRoom(layout.getStartingRoom());
        roomList = layout.getRoomList();
    }

//    @Test
//    public void deserializeCheckObject() {
//
//        assertTrue(layout instanceof Layout);
//    }
//
//    @Test
//    public void deserializeNotNull() throws com.google.gson.JsonParseException {
//
//        assertNotEquals(null, layout);
//    }

    @Test
    public void testStartWithGo() {

        assertTrue(Main.startWithGo("go EaST"));
        assertTrue(Main.startWithGo("Go wesT"));
        assertFalse(Main.startWithGo("akdj;le go akj"));
        assertFalse(Main.startWithGo("gopher"));
    }


    //test the starting room:MatthewsStreet
    //"directions": ["directionName": "East",
    //               "room": "SiebelEntry", ...

    @Test
    public void testIsValidDirection() {

        String validDirection = "eAsT";
        String invalidDirection1 = "NoRTH";
        String invalidDirection2 = "soUth";
        String invalidDirection3 = "west";
        String invalidDirection4 = "NorTHeAst";

        assertTrue(Main.isValidDirection(validDirection, layout));
        assertEquals("SiebelEntry", layout.getNextRoomName());
        assertFalse(Main.isValidDirection(invalidDirection1,layout));
        assertFalse(Main.isValidDirection(invalidDirection2, layout));
        assertFalse(Main.isValidDirection(invalidDirection3,layout));
        assertFalse(Main.isValidDirection(invalidDirection4,layout));
    }

//    @Test
//    public void testCheckEnd() {
//
//        String isEnd = "Siebel1314";
//        String notEnd = "SiebelNorthHallway";
//        String endingRoom = layout.getEndingRoom();
//
//        assertTrue(Main.checkEnd(isEnd, endingRoom));
//        assertFalse(Main.checkEnd(notEnd, endingRoom));
//    }
//
//    @Test
//    public void testExitQuit() {
//
//        String leave1 = "QuiT";
//        String leave2 = "EXit";
//
//        int roomSize = roomList.size();
//        int randomIndex = rand.nextInt(roomSize);
//        String stay = roomList.get(randomIndex).getRoomName();
//
//        assertTrue(Main.leave(leave1));
//        assertTrue(Main.leave(leave2));
//        assertFalse(Main.leave(stay));
//    }
}

