import com.google.gson.Gson;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        if(args.length != 1) {

            System.err.println("Invalid program argument.");
            return;
        }

        String json =  Input.processInput(args[0]);
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

            System.out.print("From here, you can go: ");
            Output.printDirections(layout.getCurrentDirectionList());
            userInput = Input.getUserInput();

            if(toLeave(userInput)) {

                isEnd = true;
            }else if(startWithGo(userInput)){

                int afterGo = "go ".length();
                String userDirection = userInput.substring(afterGo).trim();

                if(isValidDirection(userDirection,layout)) {

                    layout.setCurrentRoom(layout.getNextRoomName());
                    isEnd = checkEnd(layout.getCurrentRoom().getRoomName(), endingRoom);
                    if(isEnd) {Output.end();}
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

    public static boolean isValidDirection(String userDirection, Layout layout) throws IOException{

        for(Direction direction : layout.getCurrentDirectionList()) {

            if(userDirection.equalsIgnoreCase(direction.getDirectionName())) {

                if(!direction.getIsDirectionEnabled() && !ifForward(layout.getPlayer(), direction)) {

                    Output.directionDenied(direction.getDirectionName());
                    return false;
                }else{

                    layout.setNextRoomName(direction.getRoomInDirection());
                    return true;
                }
            }
        }
        Output.invalidDirection(userDirection);
        return false;
    }

    public static boolean checkEnd(String currentRoomName, String endingRoom) {

       return currentRoomName.equalsIgnoreCase(endingRoom);
    }

    public static boolean toLeave(String userInput) {

        return userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit");
    }

    private static boolean ifForward(Player player, Direction direction) throws IOException{

        if(!willing()) return false;

        List<String> validKeyList = direction.getValidKeyNamesList();
        Output.validKey(validKeyList);
        Output.playerItem(player.getItemNameList());
        String itemName = Output.format(Input.getUserInput());
        return validKeyList.contains(itemName) && player.use(itemName);
    }

    private static boolean willing() throws IOException{

        Output.askPermission();
        String userInput = Input.getUserInput();
        return userInput.equalsIgnoreCase("yes");
    }


}
