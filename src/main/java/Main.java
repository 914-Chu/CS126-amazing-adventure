import com.google.gson.Gson;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        if(args.length != 1) {

            System.err.println("Invalid program argument.");
            return;
        }
        Input input = new Input();
        String json =  input.processInput(args[0]);
        if(json == null) {

            System.err.println("Json parsing failed");
            return;
        }

        Layout layout = new Gson().fromJson(json, Layout.class);
        String startingRoom = layout.getStartingRoom();
        String endingRoom = layout.getEndingRoom();
        Room currentRoom = layout.findRoom(startingRoom);
        List<Direction> nextDirections = findNextDirections(currentRoom);
        String userInput;
        boolean isStart = true;
        boolean isEnd = false;

        do{
            System.out.println(currentRoom.getRoomDescription());
            if(isStart) {
                System.out.println("Your journey begins here");
                isStart = false;
            }
            userInput = Input.getUserInput(nextDirections);

            if(toLeave(userInput)) {

                isEnd = true;
            }else if(startWithGo(userInput)){

                int afterGo = "go ".length();
                String userDirection = userInput.substring(afterGo).trim();

                if(isValidDirection(userDirection,currentRoom)) {

                    currentRoom = layout.findRoom(currentRoom.getNextRoom());
                    String currentRoomName = currentRoom.getRoomName();
                    isEnd = checkEnd(currentRoomName, endingRoom);
                }
            }else {

                System.out.printf("I don't understand '%s'\n", userInput);
            }

        }while(!isEnd);
    }

    public static boolean startWithGo(String userInput) {

        String toLowerCase = userInput.toLowerCase().substring("go ".length());
        return(toLowerCase == "go ");
    }

    public static boolean isValidDirection(String userDirection, Room currentRoom) {

        for(Direction direction : currentRoom.getDirectionsList()) {

            String name = direction.getDirectionName();
            if(userDirection.equalsIgnoreCase(name)) {

                currentRoom.setNextRoom(name);
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

    public static boolean toLeave(String userInput) {

        return userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit");
    }

    public static List<Direction> findNextDirections(Room currentRoom) {

        List<Direction> next = new ArrayList<>();
        for(Direction direction : currentRoom.getDirectionsList()){
            next.add(direction);
        }
        return next;
    }

}
