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
        String userInput;
        boolean isStart = true;
        boolean isEnd = false;

        do{

            if(isStart) {
                System.out.println("Your journey begins here");
                isStart = false;
            }
            Output.description(layout.getCurrentRoom());
            pickOrNot(layout.getPlayer(), layout.getCurrentRoom());
            Output.directions(layout.getDirectionNameList());
            userInput = Input.getUserInput();

            if(toLeave(userInput)) {

                isEnd = true;
            }else if(startWithGo(userInput)){

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

    public static boolean startWithGo(String userInput) {

        String toLowerCase = userInput.toLowerCase().substring(0, "go ".length());
        return(toLowerCase.equals("go "));
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

    public static void pickOrNot(Player player, Room currentRoom) {

        
    }

    private static boolean ifForward(Player player, List<String> validKeyList) throws IOException{

        if(!willing()) return false;

        Output.options(validKeyList, player.getItemNameList());
        String itemName = Output.format(Input.getUserInput());

        return player.use(itemName) && inValidKeyList(itemName, validKeyList);
    }

    private static boolean willing() throws IOException{

        Output.askIfUse();
        String userInput = Input.getUserInput();

        return userInput.equalsIgnoreCase("yes");
    }

}
