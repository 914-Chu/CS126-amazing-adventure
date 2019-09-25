import java.util.*;

public class Layout {

    private String startingRoom;
    private String endingRoom;
    private List<Room> rooms;
    private Player player;

    private Room currentRoom;
    private List<String> currentDirectionNameList;
    private List<String> roomNameList;

    public String getStartingRoom() { return startingRoom;}
    public String getEndingRoom() {return endingRoom;}
    public List<Room> getRoomList() {return rooms;}
    public Player getPlayer() {return player;}
    public Room getCurrentRoom() {return currentRoom;}
    public List<String> getDirectionNameList() {return currentDirectionNameList;}
    public List<String> getRoomNameList() {return roomNameList;}

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
            currentDirectionNameList = generateDirectionNameList();
        }
    }

    public Direction findDirection(String directionName) {

        for(Direction direction : currentRoom.getDirectionsList()) {

            if(directionName.equalsIgnoreCase(direction.getDirectionName())){

                return direction;
            }
        }
        return null;
    }


    public boolean isValidDirection(String direction) {

        for(String name : currentDirectionNameList) {

            if(name.equalsIgnoreCase(direction)) {

                return true;
            }
        }
        return false;
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

        for(Direction direction : currentRoom.getDirectionsList()) {
            directionNameList.add(direction.getDirectionName());
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
