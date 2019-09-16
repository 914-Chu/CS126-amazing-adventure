public class Room {

    private String name;
    private String description;
    private Items[] items;
    private Direction[] directions;

    public String getRoomName() {return name;};
    public String getRoomDescription() {return description;};
    public Items[] getItemsList() {return items;};
    public Direction[] getDirectionsList() {return directions;};
}
