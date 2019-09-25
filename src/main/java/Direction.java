import java.util.*;

public class Direction {

    private String directionName;
    private String room;
    private boolean enabled;
    private List<String> validKeyNames;

    public String getDirectionName() {return directionName;}
    public String getRoomInDirection() {return room;}
    public boolean getIsDirectionEnabled() {return  enabled;}
    public List<String> getValidKeyNamesList() {return validKeyNames;}

    public Direction(String direction) {

        directionName = Output.format(direction);
        room = "Default room";
        enabled = true;
        validKeyNames = new ArrayList<>();
        validKeyNames.add("key0");
        validKeyNames.add("key1");
        validKeyNames.add("key2");
    }

}
