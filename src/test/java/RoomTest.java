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
    public void testGetItemsList() {

        Room room1 = layout.getRoomList().get(5);
        List<Item> expect = new ArrayList<>();
        expect.add(new Item("Bagel"));
        expect.add(new Item("Coffee"));
        List<Item> unexpect = new ArrayList<>();
        unexpect.add(new Item("Pizza"));

        assertTrue(isEqualItem(expect,room1.getItemsList()));
        assertFalse(isEqualItem(unexpect,room1.getItemsList()));
    }

    @Test
    public void testGetDirectionsList() {

        Direction direction = layout.getRoomList().get(1).getDirectionsList().get(1);
        List<String> expectValidKey = new ArrayList<>(Arrays.asList("Id", "Shoe"));

        assertEquals("Northeast", direction.getDirectionName());
        assertEquals("AcmOffice", direction.getRoomInDirection());
        assertFalse(direction.getIsDirectionEnabled());
        assertEquals(expectValidKey, direction.getValidKeyNamesList());
    }

    @Test
    public void testGetItemNameList() {

        Room room1 = layout.getRoomList().get(4);
        List<String> expect = Arrays.asList("USB-C connector", "Grading rubric");
        assertEquals(expect, room1.getItemNameList());
    }

    private boolean isEqualItem(List<Item> list1, List<Item> list2){

        return (list1.size() == list2.size()) && isEqualItem(list1, list2, 0);
    }

    private boolean isEqualItem(List<Item> list1, List<Item> list2, int index) {

        boolean equalElement = list1.get(index).getItemName().equals(list2.get(index).getItemName());
        return index == list1.size() - 1 || (equalElement && isEqualItem(list1, list2, index + 1));
    }

}
