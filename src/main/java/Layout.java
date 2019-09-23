import java.util.*;

public class Layout {

    private String startingRoom;
    private String endingRoom;
    private List<Room> rooms;
    private Player player;
    private Room currentRoom;
    private List<Direction> currentDirectionList;
    private String nextRoomName;

    public String getStartingRoom() { return startingRoom;}
    public String getEndingRoom() {return endingRoom;}
    public List<Room> getRoomList() {return rooms;}
    public Player getPlayer() {return player;}
    public Room getCurrentRoom() {return currentRoom;}
    public List<Direction> getCurrentDirectionList() {return  currentDirectionList;}
    public String getNextRoomName() {return nextRoomName;}


    public Room findRoom(String room) {

        for (Room r : rooms) {

            if (r.getRoomName().equals(room)) {
                return r;
            }
        }
        return null;
    }

    public void setCurrentRoom(String currentRoom) {

        this.currentRoom = findRoom(currentRoom);
        currentDirectionList = findDirections(this.currentRoom);
    }

    public static List<Direction> findDirections(Room room) {

        return new ArrayList<>(room.getDirectionsList());
    }

    public void setNextRoomName(String nextRoomName) {

        this.nextRoomName = nextRoomName;
    }

}
