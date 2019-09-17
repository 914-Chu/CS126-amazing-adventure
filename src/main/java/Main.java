import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException{


        String json =  URLConverter.getJson();
        Layout layout = new Gson().fromJson(json, Layout.class);
        String startingRoom = layout.getStartingRoom();
        String endingRoom = layout.getEndingRoom();
        String userInput;
        Room currentRoom = layout.findRoom(startingRoom);
        boolean isStart = true;
        boolean isEnd = false;

        do{
            System.out.println(currentRoom.getRoomDescription());
            if(isStart) {
                System.out.println("Your journey begins here");
                isStart = false;
            }
            userInput = getUserInput(currentRoom);

            if(userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit")) {

                isEnd = true;
            }else if(checkInput(userInput)){

                if(checkDirection(userInput,currentRoom)) {

                    currentRoom = layout.findRoom(currentRoom.getNextRoom());
                    isEnd = checkEnd(currentRoom, endingRoom);
                }
            }else {

                System.out.printf("I don't understand '%s'\n", userInput);
            }

        }while(!isEnd);
    }

    public static String getUserInput(Room currentRoom) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("From here, you can go: ");
        printDirections(currentRoom);
        String userInput = reader.readLine();
        return userInput;
    }

    public static void printDirections(Room room) {

        int listSize =  room.getDirectionsList().size();
        List<Direction> directionList = room.getDirectionsList();
        String direction;
        if(listSize > 1) {

            for (int i = 0; i < listSize - 1; i++) {

                direction = directionList.get(i).getDirectionName();
                System.out.print(direction);
                System.out.print(", ");
            }
            direction = directionList.get(listSize-1).getDirectionName();
            System.out.printf("or %s\n", direction);
        }else {

            System.out.println(directionList.get(0).getDirectionName());
        }
    }

    public static boolean checkDirection(String userInput, Room room) {

        String userDirection = userInput.substring(3);
        for(Direction direction : room.getDirectionsList()) {

            String directionsInList = direction.getDirectionName();
            if(userDirection.equalsIgnoreCase(directionsInList)) {

                room.findNextRoom(directionsInList);
                return true;
            }
        }
        System.out.println("I can't go " + userDirection);
        return false;
    }

    public static boolean checkEnd(Room currentRoom, String endingRoom) {

        boolean isEnd = currentRoom.getRoomName().equalsIgnoreCase(endingRoom);

        if(isEnd) {
            System.out.println("You've reached the ending room, thank you for playing.");
        }

        return isEnd;
    }

    public static boolean checkInput(String userInput) {

        String go = "go";
        String toLowerCase = userInput.toLowerCase();
        return(toLowerCase.charAt(0) == go.charAt(0) && toLowerCase.charAt(1) == go.charAt(1));
    }

}
