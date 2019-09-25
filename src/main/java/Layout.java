import java.util.*;

public class Layout {

    private String startingRoom;
    private String endingRoom;
    private List<Room> rooms;
    private Player player;

    private Room currentRoom;
    private List<Direction> currentDirectionList;
    private List<String> directionNameList;
    private List<String> roomNameList;
    private String nextRoomName;

    public String getStartingRoom() { return startingRoom;}
    public String getEndingRoom() {return endingRoom;}
    public List<Room> getRoomList() {return rooms;}
    public Player getPlayer() {return player;}
    public Room getCurrentRoom() {return currentRoom;}
    public List<Direction> getCurrentDirectionList() {return  currentDirectionList;}
    public List<String> getDirectionNameList() {return directionNameList;}
    public List<String> getRoomNameList() {return roomNameList;}
    public String getNextRoomName() {return nextRoomName;}


    public Room findRoom(String room) {

        for (Room r : rooms) {

            if (r.getRoomName().equalsIgnoreCase(room)) {
                return r;
            }
        }
        return null;
    }

    public void setCurrentRoom(String currentRoom) {

        if(roomNameList == null) {
            roomNameList = generateRoomNameList();
        }
        if(isValidRoom(currentRoom)) {

            this.currentRoom = findRoom(currentRoom);
            currentDirectionList = findDirections(this.currentRoom);
            directionNameList = generateDirectionNameList();
        }
    }

    public List<Direction> findDirections(Room room) {

        return new ArrayList<>(room.getDirectionsList());
    }

    public void setNextRoomName(String nextRoomName) {

        if(isValidRoom(nextRoomName)){
            this.nextRoomName = nextRoomName;
        }
    }

    public boolean isValidRoom(String room) {

        for(String existRoom : roomNameList) {

            if(existRoom.equalsIgnoreCase(room)){
                return true;
            }
        }
        Output.invalidRoom(room);
        return false;
    }

    private List<String> generateDirectionNameList() {

        List<String> directionNameList = new ArrayList<>();

        for(Direction direction : currentDirectionList) {
            directionNameList.add(Output.format(direction.getDirectionName()));
        }
        return directionNameList;
    }

    private List<String> generateRoomNameList() {

        List<String> roomNameList = new ArrayList<>();

        for(Room room : rooms) {
            roomNameList.add(room.getRoomName());
        }
        return roomNameList;
    }

}
