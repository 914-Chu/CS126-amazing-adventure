import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class RoomTest {

    Layout layout;
    @Before
    public void setUp() throws Exception {

        String json = Input.getJson("content");
        layout = new Gson().fromJson(json, Layout.class);
        layout.setCurrentRoom(layout.getStartingRoom());
    }

    @Test
    public void testGetName() {

        Room room1 = layout.getRoomList().get(0);

        assertEquals("MatthewsStreet", room1.getRoomName());
    }

    @Test
    public void testGetDescription() {

        Room room1 = layout.getRoomList().get(2);
        assertEquals("You are in the ACM office.  There are lots of friendly ACM people.", room1.getRoomDescription());
    }

    @Test
    public void testGetItemList() {

        Room room1 = layout.getRoomList().get(5);
        List<Item> expect = new ArrayList<>();
        expect.add(new Item("Bagel"));
        expect.add(new Item("Coffee"));

        assertEquals(expect.get(0), room1.getItemsList().get(0));
        assertEquals(expect.get(1), room1.getItemNameList().get(1));
    }

    @Test
    public void testGetItemNameList() {

        Room room1 = layout.getRoomList().get(4);
        List<String> expect = Arrays.asList("USB-C connector", "Grading rubric");
        assertEquals(expect, room1.getItemNameList());
    }

}
