import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{


        String json =  URLConverter.getJson();
        if(json == null) {
            return;
        }
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

            if(leave(userInput)) {

                isEnd = true;
            }else if(checkInput(userInput)){

                int afterGo = 3;
                String userDirection = userInput.substring(afterGo);

                if(checkDirection(userDirection,currentRoom)) {

                    currentRoom = layout.findRoom(currentRoom.getNextRoom());
                    String currentRoomName = currentRoom.getRoomName();
                    isEnd = checkEnd(currentRoomName, endingRoom);
                }
            }else {

                System.out.printf("I don't understand '%s'\n", userInput);
            }

        }while(!isEnd);
    }

    public static boolean checkInput(String userInput) {

        String go = "go ";
        String toLowerCase = userInput.toLowerCase().substring(go.length());
        return(toLowerCase == go);
    }

    public static boolean checkDirection(String userDirection, Room room) {

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

    public static boolean checkEnd(String currentRoomName, String endingRoom) {

        boolean isEnd = currentRoomName.equalsIgnoreCase(endingRoom);

        if(isEnd) {
            System.out.printf("You've reached the ending room %s, thank you for playing.\n", endingRoom);
        }

        return isEnd;
    }

    public static boolean leave(String userInput) {

        return userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit");
    }

    private static String getUserInput(Room currentRoom) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("From here, you can go: ");
        printDirections(currentRoom);
        return reader.readLine();
    }

    private static void printDirections(Room room) {

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
}
