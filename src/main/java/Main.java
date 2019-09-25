import com.google.gson.Gson;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        if(args.length != 1) {

            System.err.println("Invalid program argument.");
            return;
        }

        String json =  Input.getJson(args[0]);
        if(json == null) {

            System.err.println("Json parsing failed");
            return;
        }

        Layout layout = new Gson().fromJson(json, Layout.class);
        String startingRoom = layout.getStartingRoom();
        String endingRoom = layout.getEndingRoom();
        layout.setCurrentRoom(startingRoom);
        Computer computer = new Computer();
        String userInput;
        boolean isStart = true;
        boolean isEnd = false;

        Output.mission();

        do{

            if(isStart) {
                System.out.println("Your journey begins here");
                isStart = false;
            }
            Output.description(layout.getCurrentRoom());
            Output.playerItem(layout.getPlayer().getItemNameList());
            pickOrNot(layout.getPlayer(), layout.getCurrentRoom());
            Output.directions(layout.getDirectionNameList());
            userInput = Input.getUserInput();

            if(toLeave(userInput)) {

                isEnd = true;
            }else if(startWith(userInput, "go ")){

                int afterGo = "go ".length();
                String userDirection = userInput.substring(afterGo).trim();

                if(layout.isValidDirection(userDirection)) {

                    Direction direction = layout.findDirection(userDirection);
                    if(!direction.getIsDirectionEnabled() && !ifForward(layout.getPlayer(), direction.getValidKeyNamesList())) {

                        Output.directionDenied(direction.getDirectionName());
                    }else {

                        layout.setCurrentRoom(direction.getRoomInDirection());
                        isEnd = checkEnd(layout.getCurrentRoom().getRoomName(), endingRoom);
                        if (isEnd) { Output.end(); }
                    }
                }else {

                    Output.invalidDirection(userDirection);
                }

            }else {

                Output.unknown(userInput);
            }

        }while(!isEnd);
    }

    public static boolean startWith(String userInput, String action) {

        String toLowerCase = userInput.toLowerCase().substring(0, action.length());
        return(toLowerCase.equals(action));
    }

    public static boolean checkEnd(String currentRoomName, String endingRoom) {

       return currentRoomName.equalsIgnoreCase(endingRoom);
    }

    public static boolean toLeave(String userInput) {

        return userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit");
    }

    public static boolean inValidKeyList(String itemName, List<String> validKeyList) {

        for(String validKey : validKeyList) {
            if(validKey.equalsIgnoreCase(itemName)){
                return true;
            }
        }
        Output.wrongItem(itemName);
        return false;
    }

    public static void pickOrNot(Player player, Room currentRoom) throws IOException{

        Output.askAction();
        while(willing()){

            Output.pickOrLeave();
            String action = Input.getUserInput();
            if(startWith(action, "pick up")) {

                int afterAction = "pick up".length();
                String item = Output.format(action.substring(afterAction).trim());
                if(currentRoom.took(item)) {
                    player.pickUp(item);
                }
            }else if(startWith(action, "leave ")) {

                int afterAction = "leave ".length();
                String item = Output.format(action.substring(afterAction).trim());
                if(player.leave(item)) {
                    currentRoom.left(item);
                }
            }
            Output.askAction();
        }

    }

    private static boolean ifForward(Player player, List<String> validKeyList) throws IOException{

        Output.askIfUse();
        if(!willing()) return false;

        Output.options(validKeyList);
        Output.playerItem(player.getItemNameList());
        String itemName = Output.format(Input.getUserInput());

        return player.leave(itemName) && inValidKeyList(itemName, validKeyList);
    }

    private static boolean willing() throws IOException{

        String userInput = Input.getUserInput();

        return userInput.equalsIgnoreCase("yes");
    }

}
