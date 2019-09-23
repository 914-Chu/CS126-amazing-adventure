import java.util.*;

public class Player {

    private List<Item> items;

    public List<Item> getPlayerItemList() {return items;}

    public boolean use(String item) {

        return items.contains(item);
    }

    public boolean pickUp(String item) {

        if(!items.contains(item)){
            
            items.add(new Item(item));
            return true;
        }
        Output.printDuplicate(item);
        return false;
    }
}
