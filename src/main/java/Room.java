import java.util.*;

public class Room {

    private String name;
    private String description;
    private List<Item> items;
    private List<Direction> directions;
    private String nextRoom;

    public String getRoomName() {return name;}
    public String getRoomDescription() {return description;}
    public List<Item> getItemsList() {return items;}
    public List<Direction> getDirectionsList() {return directions;}
    public String getNextRoom() {return nextRoom;}
    public void setNextRoom(String nextRoom) { this.nextRoom = nextRoom;}

}
