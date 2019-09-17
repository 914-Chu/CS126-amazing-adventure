import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.net.*;

public class MAIN {

    public static void main(String[] args) throws IOException{

        //String content =  URLReader(new URL("https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json"));
        String content = Data.getFileContents("src", "test", "test_resources", "content");
        Layout layout = new Gson().fromJson(content, Layout.class);
        String startingRoom = layout.getStartingRoom();
        String endingRoom = layout.getEndingRoom();
        Room current = layout.findRoom(startingRoom);
        boolean status = true;

        System.out.println(current.getRoomDescription());
        System.out.println("Your journey begins here");

        do{
            System.out.print("From here, you can go: ");
            printDirections(current);
            
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
    }
}
