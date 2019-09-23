import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class LayoutTest {

    private Layout layout;
    private List<Room> roomList;
    private Random rand = new Random();

    @Before
    public void setUp() throws Exception {

        String url = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
        Gson gson = new Gson();
        String content = URLConverter.getJson(url);
        layout = gson.fromJson(content, Layout.class);
        roomList = layout.getRoomList();
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

        int randomIndex = rand.nextInt(roomList.size());
        String expectName = roomList.get(randomIndex).getRoomName();
        String actualName = layout.findRoom(expectName).getRoomName();

        String unexpectName = "ad;lfkje";
        Room roomNotFount = layout.findRoom(unexpectName);

        assertEquals(expectName, actualName);
        assertNull(roomNotFount);
    }

    @Test
    public void testRoomListSize() {

        int actualSize = 0;
        int expectSize = roomList.size();
        for(Room room : roomList) {
            actualSize++;
        }

        assertEquals(expectSize, actualSize);
    }
}
