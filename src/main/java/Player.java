import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class Player {

    private List<Item> items = new ArrayList<>();

    public List<Item> getPlayerItemList() {return items;}

    public Player() {

        items.add(new Item("Cup"));
        items.add(new Item("Backpack"));
        items.add(new Item("Phone"));
    }

    public boolean use(String item) {

        List<String> nameList = getItemNameList();
        for(int i = 0; i < nameList.size(); i++) {

            if(nameList.get(i).equalsIgnoreCase(item)) {

                items.remove(i);
                return true;
            }
        }
        Output.noItem(Output.format(item));
        return false;
    }

    public boolean pickUp(String item) {

        List<String> nameList = getItemNameList();
        for(int i = 0; i < nameList.size(); i++) {

            if(nameList.get(i).equalsIgnoreCase(item)) {

                Output.duplicate(Output.format(item));
                return false;
            }
        }
        items.add(new Item(item));
        return true;
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
            itemNameList.add(Output.format(item.getItemName()));
        }
        return itemNameList;
    }

}
