import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.net.*;

public class MAIN {

    public static void main(String[] args) throws IOException{

        String content =  URLReader(new URL("https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json"));
        Layout layout = new Gson().fromJson(content, Layout.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String startingRoom = layout.getStartingRoom();
        String endingRoom = layout.getEndingRoom();
        String userInput;
        String userDirection;
        Room currentRoom = layout.findRoom(startingRoom);
        boolean valid;
        boolean status = true;

        System.out.println(currentRoom.getRoomDescription());
        System.out.println("Your journey begins here");

        do{
            System.out.print("From here, you can go: ");
            printDirections(currentRoom);
            userInput = reader.readLine();

            if(userInput.equalsIgnoreCase("exit") || userDirection.equalsIgnoreCase("quit")) {

                status = false;
            }else if(validateDirection(userDirection, currentRoom)){

                currentRoom = layout.findRoom(currentRoom.getNextRoom());
                System.out.println(currentRoom.getRoomDescription());
            }
        }while(status);
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

    public static void printDirections(Room room) {

        for(Direction direction : room.getDirectionsList()) {
            System.out.print(direction.getDirectionName() + " ");
        }
        System.out.println();
    }

    public static boolean validateDirection(String userInput, Room room) {

        String userDirection = userInput.substring(3);
        for(Direction direction : room.getDirectionsList()) {

            String name = direction.getDirectionName();
            if(userDirection.equalsIgnoreCase(name)) {

                room.findNextRoom(userDirection);
                return true;
            }
        }
        findErrorType(userDirection);
        return false;
    }

    public static void findErrorType(String userDirection) {

        String go = "go";

        if(userDirection.charAt(0) == go.charAt(0) && userDirection.charAt(1) == go.charAt(1)) {

            System.out.println("I can't go " + userDirection);
        }else {

            System.out.printf("I don't understand '%s'", userDirection);
        }

    }

}
