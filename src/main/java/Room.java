import java.util.*;

public class Room {

    private String name;
    private String description;
    private List<Item> items;
    private List<Direction> directions;

    public String getRoomName() {return name;}
    public String getRoomDescription() {return description;}
    public List<Item> getItemsList() {return items;}
    public List<Direction> getDirectionsList() {return directions;}

    public List<String> getItemNameList() {

        List<String> itemNameList = new ArrayList<>();

        for(Item item: items) {
            itemNameList.add(item.getItemName());
        }
        return itemNameList;
    }

    public boolean took(String item) {

        List<String> nameList = getItemNameList();
        for(int i = 0; i < nameList.size(); i++) {

            if(nameList.get(i).equalsIgnoreCase(item)) {

                items.remove(i);
                return true;
            }
        }
        Output.notExist(Output.format(item));
        return false;
    }

    public void left(String item) {

        boolean exist = false;
        List<String> nameList = getItemNameList();
        for(int i = 0; i < nameList.size(); i++) {

            if(nameList.get(i).equalsIgnoreCase(item)) {

                exist = true;
            }
        }

        if(!exist) items.add(new Item(item));
    }
}
