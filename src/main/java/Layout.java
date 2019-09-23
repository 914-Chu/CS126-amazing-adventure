import java.util.*;

public class Layout {

    private String startingRoom;
    private String endingRoom;
    private List<Room> rooms;
    private Player player;
    private Room currentRoom;
    private List<Direction> currentDirectionList;

    public String getStartingRoom() { return startingRoom;}
    public String getEndingRoom() {return endingRoom;}
    public List<Room> getRoomList() {return rooms;}
    public Player getPlayer() {return player;}
    public Room getCurrentRoom() {return currentRoom;}
    public List<Direction> getCurrentDirectionList() {return  currentDirectionList;}


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

        List<Direction> directionList = new ArrayList<>();
        for(Direction direction : room.getDirectionsList()){
            directionList.add(direction);
        }
        return directionList;
    }

}
