import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class MainTest {

    private Layout layout;
    private List<Room> roomList;
    private Random rand = new Random();

    @Before
    public void setUp() throws Exception {

        String url = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        Gson gson = new Gson();
        String content = URLConverter.getJson(url);
        layout = gson.fromJson(content, Layout.class);
        roomList = layout.getRoom();
    }

    @Test
    public void deserializeNotNull() throws com.google.gson.JsonParseException {

        assertNotEquals(null, layout);
    }

    @Test
    public void testCheckInput() {

        String beginWithoutGo = "akdj;le go akj";
        assertEquals(false, Main.checkInput(beginWithoutGo));
    }

    @Test
    public void testCheckDirection() {

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

        String isEnd = "Siebel1314";
        String notEnd = "SiebelNorthHallway";
        String endingRoom = layout.getEndingRoom();

        assertEquals(true, Main.checkEnd(isEnd, endingRoom));
        assertEquals(false, Main.checkEnd(notEnd, endingRoom));
    }

    @Test
    public void testExitQuit() {

        String leave1 = "QuiT";
        String leave2 = "EXit";

        int roomSize = roomList.size();
        int randomIndex = rand.nextInt(roomSize);
        String stay = roomList.get(randomIndex).getRoomName();

        assertEquals(true, Main.leave(leave1));
        assertEquals(true, Main.leave(leave2));
        assertEquals(false, Main.leave(stay));
    }
}

