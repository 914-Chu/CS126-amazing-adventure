import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException{

        String content =  URLReader(new URL("https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json"));
        Layout layout = new Gson().fromJson(content, Layout.class);
        String startingRoom = layout.getStartingRoom();
        String endingRoom = layout.getEndingRoom();
        String userInput;
        Room currentRoom = layout.findRoom(startingRoom);
        boolean start =true;
        boolean end = false;

        do{
            System.out.println(currentRoom.getRoomDescription());
            if(start) {
                System.out.println("Your journey begins here");
                start = false;
            }
            userInput = getUserInput(currentRoom);

            if(userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit")) {

                end = true;
            }else if(checkInput(userInput)){

                if(checkDirection(userInput,currentRoom)) {
                    currentRoom = layout.findRoom(currentRoom.getNextRoom());
                    if(checkEnd(currentRoom, endingRoom)) {

                        end = true;
                        System.out.println("You've reached the ending room, thank you for playing.");
                    };
                }
            }else {

                System.out.printf("I don't understand '%s'\n", userInput);
            }

        }while(!end);
    }

    public static String URLReader(URL url) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        InputStream in = url.openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } finally {
            in.close();
        }

        return sb.toString();
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

        return currentRoom.getRoomName().equalsIgnoreCase(endingRoom);
    }

    public static boolean checkInput(String userInput) {

        String go = "go";
        String toLowerCase = userInput.toLowerCase();
        return(toLowerCase.charAt(0) == go.charAt(0) && toLowerCase.charAt(1) == go.charAt(1));
    }

}
