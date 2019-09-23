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
        layout.setCurrentRoom(startingRoom);
        String userInput;
        boolean isStart = true;
        boolean isEnd = false;

        do{
            System.out.println(layout.getCurrentRoom().getRoomDescription());
            if(isStart) {
                System.out.println("Your journey begins here");
                isStart = false;
            }
            userInput = Input.getUserInput(layout.getCurrentDirectionList());

            if(toLeave(userInput)) {

                isEnd = true;
            }else if(startWithGo(userInput)){

                int afterGo = "go ".length();
                String userDirection = userInput.substring(afterGo).trim();

                if(isValidDirection(userDirection,layout)) {

                    isEnd = checkEnd(layout.getCurrentRoom().getRoomName(), endingRoom);
                    if(isEnd) {Output.printEnd();}
                }
            }else {

                Output.printUnknown(userInput);
            }

        }while(!isEnd);
    }

    public static boolean startWithGo(String userInput) {

        String toLowerCase = userInput.toLowerCase().substring(0, "go ".length());
        return(toLowerCase.equals("go "));
    }

    public static boolean isValidDirection(String userDirection, Layout layout) {

        for(Direction direction : layout.getCurrentDirectionList()) {

            String directionName = direction.getDirectionName();
            if(userDirection.equalsIgnoreCase(directionName)) {


                layout.setCurrentRoom(direction.getRoomInDirection());
                return true;
            }
        }
        System.out.println("I can't go " + userDirection);
        return false;
    }

    public static boolean checkEnd(String currentRoomName, String endingRoom) {

       return currentRoomName.equalsIgnoreCase(endingRoom);
    }

    public static boolean toLeave(String userInput) {

        return userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit");
    }

}
