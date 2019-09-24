import java.util.*;

public class Output {

    public static void printDirections(List<Direction> directionList) {

        int listSize =  directionList.size();
        if(listSize == 0) {
            System.err.println("Empty direction list");
            return;
        }
        else if(listSize > 1) {

            for (int i = 0; i < listSize - 1; i++) {

                System.out.print(directionList.get(i).getDirectionName());
                System.out.print(", ");
            }
            System.out.printf("or %s\n", directionList.get(listSize-1).getDirectionName());
        }else {

            System.out.println(directionList.get(0).getDirectionName());
        }
    }

    public static void printEnd() {

        System.out.println("You've reached the ending room, thank you for playing");
    }

    public static void printUnknown(String userInput) {

        System.out.printf("I don't understand '%s'\n", userInput);
    }

    public static void printInvalid(String userDirection) {

        String invalid = Character.toUpperCase(userDirection.charAt(0)) + userDirection.substring(1).toLowerCase();;
        System.out.println("I can't go " + invalid + " from here.");
    }

    public static void printDuplicate(String itemName) {

        System.out.println("You already have " + itemName);
    }

    public static void printNoItem(String itemName) {

        System.out.println("You don't have " + itemName);
    }

    public static void printValidKey(List<String> validKeyList) {

        System.out.println("You can enable this direction by using:");
        for(String validKey : validKeyList) {

            System.out.println(validKey);
        }
    }

    public static void printDirectionDenied(String directionName) {

        System.out.println("You've been denied to go " + directionName);
    }

    public static void printIfUseAgain() {

        System.out.println("Do you want to try using another item?");
    }
}
