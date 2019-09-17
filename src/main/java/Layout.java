import java.util.*;

public class Layout {

    private String startingRoom;
    private String endingRoom;
    private List<Room> rooms;

    public String getStartingRoom() { return startingRoom;}
    public String getEndingRoom() {return endingRoom;};
    public List<Room> getRoom() {return rooms;};

    public Room findRoom(String room) {

        for (Room r : rooms) {

            if (r.getRoomName().equals(room)) {
                return r;
            }
        }
        return null;
    }

}
