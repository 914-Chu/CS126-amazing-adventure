import java.util.*;

public class Player {

    private List<Item> items;

    public List<Item> getPlayerItemList() {return items;}


    public boolean use(String itemName) {

        if (inList(itemName)) {

            items.remove(new Item(itemName));
            return true;
        }
        Output.noItem(itemName);
        return false;
    }

    public boolean pickUp(String itemName) {

        if(!inList(itemName)){

            items.add(new Item(itemName));
            return true;
        }
        Output.duplicate(itemName);
        return false;
    }

    public boolean inList(String itemName) {

        for(String name : getItemNameList()) {

            if (name.equalsIgnoreCase(itemName)) {

                return true;
            }
        }
        return false;
    }

    public List<String> getItemNameList() {

        List<String> itemNameList = new ArrayList<>();
        for(Item item : items) {
            itemNameList.add(item.getItemName());
        }
        return itemNameList;
    }

}
