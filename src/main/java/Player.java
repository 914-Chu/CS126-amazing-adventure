import java.util.*;

public class Player {

    private List<Item> items = new ArrayList<>();

    public List<Item> getPlayerItemList() {return items;}

    public Player() {

        items.add(new Item("Cup"));
        items.add(new Item("Backpack"));
        items.add(new Item("Phone"));
    }

    public boolean leave(String item) {

        List<String> nameList = getItemNameList();
        for(int i = 0; i < nameList.size(); i++) {

            if(nameList.get(i).equalsIgnoreCase(item)) {

                items.remove(i);
                Output.leave(item);
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
        Output.pick(item);
        items.add(new Item(item));
        return true;
    }

    public List<String> getItemNameList() {

        List<String> itemNameList = new ArrayList<>();

        for(Item item : items) {
            itemNameList.add(Output.format(item.getItemName()));
        }
        return itemNameList;
    }

}
