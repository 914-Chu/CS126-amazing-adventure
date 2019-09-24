import java.util.*;

public class Output {

    public static void description(Room currentRoom) {

        if(currentRoom.getItemsList().size() == 0) {
            System.out.println("Empty item list");
            return;
        }
        System.out.println(currentRoom.getRoomDescription());
        System.out.print("You can see ");
        formatList(currentRoom.getItemNameList());
        System.out.println(" here.");
    }

    public static void directions(List<String> directionNameList) {

        if(directionNameList.size() == 0) {
            System.out.println("Empty direction list");
            return;
        }
        formatList(directionNameList);
        System.out.println();
    }

    public static void end() {

        System.out.println("You've reached the ending room, thank you for playing");
    }

    public static void unknown(String userInput) {

        System.out.printf("I don't understand '%s'\n", userInput);
    }

    public static void invalidDirection(String userDirection) {

        String invalid = format(userDirection);
        System.out.println("I can't go " + invalid + " from here.");
    }

    public static void duplicate(String itemName) {

        System.out.println("You already have " + itemName);
    }

    public static void noItem(String itemName) {

        System.out.println("You don't have " + itemName);
    }

    public static void options(List<String> validKeyList, List<String> playerItemList) {

        System.out.println("You can enable this direction by using:");
        for(String validKey : validKeyList) {
            System.out.print(validKey + " ");
        }
        System.out.println("\nYour items:");
        for(String item: playerItemList) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void directionDenied(String directionName) {

        System.out.println("You've been denied to go " + directionName);
    }

    public static void askPermission() {

        System.out.println("Do you want to try enabling this direction with items? (Enter yes/anything else will be considered no)");
    }

    public static void wrongItem(String itemName) {

        System.out.println(itemName + " can't be used to enable this direction.");
    }

    public static String format(String input) {

        String trim = input.trim();
        return Character.toUpperCase(trim.charAt(0)) + trim.substring(1).toLowerCase();
    }

    private static void formatList(List<String> list) {

        int listSize = list.size();

        if(listSize > 1) {

            for (int i = 0; i < listSize - 1; i++) {

                System.out.print(list.get(i));
                System.out.print(", ");
            }
            System.out.print(list.get(listSize-1));
        }else {

            System.out.print(list.get(0));
        }
    }

}
