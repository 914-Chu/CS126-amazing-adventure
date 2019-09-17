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
        String startingRoom = layout.getStartingRoom();
        String endingRoom = layout.getEndingRoom();
        String userDirection;
        Room currentRoom = layout.findRoom(startingRoom);
        boolean status = true;

        System.out.println(currentRoom.getRoomDescription());
        System.out.println("Your journey begins here");

        do{
            System.out.print("From here, you can go: ");
            printDirections(currentRoom);
            userDirection = getUserInput();

            if(userDirection.equalsIgnoreCase("exit") || userDirection.equalsIgnoreCase("quit")) {

                status = false;
            }else if(validateDirection(userDirection, currentRoom)){

                currentRoom = layout.findRoom(currentRoom.getNextRoom());
                System.out.println(currentRoom.getRoomDescription());
            }else {

                findErrorType(userDirection);
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
            System.out.print(direction.getDirectionName());
        }
        System.out.println();
    }

    public static boolean validateDirection(String userDirection, Room room) {


        for(Direction direction : room.getDirectionsList()) {

            String name = direction.getDirectionName();
            if(userDirection.equalsIgnoreCase(name)) {
                room.findNextRoom(userDirection);
                return true;
            }
        }
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

    public static String getUserInput() throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = reader.readLine();
        reader.close();

        return userInput;
    }
}
