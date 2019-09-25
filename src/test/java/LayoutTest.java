import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class LayoutTest {

    Layout layout;
    Random rand;
    @Before
    public void setUp() throws Exception {

        String json = Input.getJson("content");
        layout = new Gson().fromJson(json, Layout.class);
        layout.setCurrentRoom(layout.getStartingRoom());
    }


    @Test
    public void testStartingRoom() {

        assertEquals("MatthewsStreet", layout.getStartingRoom());
    }

    @Test
    public void testEndingRoom() {

        assertEquals("Siebel1314", layout.getEndingRoom());
    }

    @Test
    public void testFindRoom() {


        Room expectRoom = layout.findRoom("SiebelBasEMEnt");
        Room roomNotFount = layout.findRoom("daoi;elkqe");
        String expectDescription = "You are in the basement of Siebel.  You see tables with students working and door to computer labs.";

        assertEquals("SiebelBasement", expectRoom.getRoomName());
        assertEquals(expectDescription, expectRoom.getRoomDescription());
        assertNull(roomNotFount);
    }

    @Test
    public void testSetCurrentRoom() {

        layout.setCurrentRoom("Siebel1112");
        assertEquals("Siebel1112", layout.getCurrentRoom().getRoomName());
        layout.setCurrentRoom("DinginRoom");
        assertNotEquals("DiningRoom", layout.getCurrentRoom().getRoomName());
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

        assertTrue(layout.isValidDirection(validDirection));
        assertFalse(layout.isValidDirection(invalidDirection1));
        assertFalse(layout.isValidDirection(invalidDirection2));
        assertFalse(layout.isValidDirection(invalidDirection3));
        assertFalse(layout.isValidDirection(invalidDirection4));
    }

    @Test
    public void testIsValidRoom() {

        assertTrue(layout.isValidRoom("SiebelNoRTHHallway"));
        assertFalse(layout.isValidRoom("Restroom"));
    }

}
