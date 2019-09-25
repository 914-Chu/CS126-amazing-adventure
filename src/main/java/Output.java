import java.util.*;

public class Output {

    public static void description(Room currentRoom) {

        System.out.println(currentRoom.getRoomDescription());
        if(currentRoom.getItemsList() == null || currentRoom.getItemsList().isEmpty()) {
            System.out.println("There's nothing here.");
        }else {

            System.out.print("You can see ");
            formatList(currentRoom.getItemNameList());
            System.out.println(" here.");
        }
    }

    public static void directions(List<String> directionNameList) {

        if(directionNameList == null || directionNameList.isEmpty()) {
            System.out.println("Empty direction list");

        }else {
            System.out.print("From here, you can go: ");
            formatList(directionNameList);
            System.out.println();
        }
    }

    public static void playerItem(List<String> itemList) {

        System.out.print("You have: ");
        formatList(itemList);
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

    public static void invalidRoom(String room) {

        System.out.println(room + " doesn't exist in the game.");
    }

    public static void duplicate(String itemName) {

        System.out.println("You already have " + itemName);
    }

    public static void leave(String itemName) {

        System.out.println("Left " + itemName + " successfully.");
    }

    public static void pick(String itemName) {

        System.out.println("Picked up " + itemName + " successfully.");
    }

    public static void noItem(String itemName) {

        System.out.println("You don't have " + itemName);
    }

    public static void notExist(String itemName) {

        System.out.println("There isn't " + itemName + " in this room.");
    }

    public static void options(List<String> validKeyList) {

        System.out.println("You can enable this direction by using:");
        for(String validKey : validKeyList) {
            System.out.print(validKey + " ");
        }
        System.out.println();
    }

    public static void directionDenied(String directionName) {

        System.out.println("You've been denied to go " + directionName);
    }

    public static void askAction() {

        System.out.println("Do you want to pick up or leave anything?(Enter yes/anything else will be considered no)");
    }

    public static void pickOrLeave() {

        System.out.println("What do you want to pick up or leave?");
    }

    public static void askIfUse() {

        System.out.println("Do you want to try enabling this direction with items? (Enter yes/anything else will be considered no)");
    }

    public static void wrongItem(String itemName) {

        System.out.println(itemName + " can't be used to enable this direction.");
    }

    public static void mission() {

        System.out.println("In order to win the game, " +
                            "find all the computer components and build it correctly at the ending room");
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
