import java.util.*;

public class Player {

    private List<Item> items;
    private List<String> itemNameList = new ArrayList<>();

    public Player() {

        if(items != null) {
            for (Item item : items) {

                itemNameList.add(item.getItemName());
            }
        }
    }

    public List<Item> getPlayerItemList() {return items;}

    public boolean use(String item) {

        if(itemNameList.contains(item)) {

            useItem(item);
            return true;
        }
        Output.printNoItem(item);
        return false;
    }

    public boolean pickUp(String item) {

        if(!itemNameList.contains(item)){

            addItem(item);
            return true;
        }
        Output.printDuplicate(item);
        return false;
    }

    private void addItem(String itemName) {

        items.add(new Item(itemName));
        itemNameList.add(itemName);
    }

    private void useItem(String itemName) {

        items.remove(new Item(itemName));
        itemNameList.remove(itemName);
    }
}
