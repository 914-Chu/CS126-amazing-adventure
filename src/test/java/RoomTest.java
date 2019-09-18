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

    @Test
    public void testGetName() {

        String actual = roomList.get(3).getRoomName();
        String expect = "SiebelNorthHallway";
        assertEquals(expect, actual);
    }

    @Test
    public void testGetDescription() {

        String actual = roomList.get(6).getRoomDescription();
        String expect = "You are in Siebel 1314.  There are happy CS 126 students doing a code review.";
        assertEquals(expect, actual);
    }

    @Test
    public void testDirectionListSize() {

        int randomIndex = rand.nextInt(roomList.size());
        List<Direction> directionList = roomList.get(randomIndex).getDirectionsList();

        int actualSize = 0;
        int expectSize = directionList.size();
        for(Direction direction : directionList) {
            actualSize++;
        }

        assertEquals(expectSize, actualSize);
    }

    @Test
    public void testFindNextRoom() {

        int roomSize = roomList.size();
        int randomIndex = rand.nextInt(roomSize);
        Room room = roomList.get(randomIndex);

        int directionSize = room.getDirectionsList().size();
        int randomIndex1 = rand.nextInt(directionSize);
        Direction direction = room.getDirectionsList().get(randomIndex1);
        
        room.findNextRoom(direction.getDirectionName());
        String expect = direction.getRoomInDirection();

        assertEquals(expect, room.getNextRoom());
    }
}
