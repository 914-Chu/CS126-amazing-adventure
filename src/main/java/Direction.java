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

}
