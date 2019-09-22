import java.io.IOException;
import java.net.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;

public class Input {

    public String processInput(String input) throws IOException{

        if(isValidPath(input)) {

            String path[] = input.split("\\\\");
            int firstPath = 0;
            int secondPath = 1;

            return Data.getFileContents(path[firstPath],path[secondPath]);
        }else if(isValidURL(input)) {

            return URLConverter.getJson(input);
        }else {

            return null;
        }
    }

    public boolean isValidURL(String toCheck) {

        try{
            URL url = new URL(toCheck);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            return true;
        }catch(MalformedURLException e) {
            System.out.println("The URL is not in a valid form");
            return false;
        }catch(IOException e) {
            System.out.println("The connection couldn't be established");
            return false;
        }
    }

    // Code below derived from:
    // https://stackoverflow.com/questions/468789/is-there-a-way-in-java-to-determine-if-a-path-is-valid-without-attempting-to-cre

    public boolean isValidPath(String path) {
        try {
            Paths.get(path);
            String paths[] = path.split("\\\\");
            return paths.length == 2;
        } catch (InvalidPathException | NullPointerException e) {
            return false;
        }
    }


}
