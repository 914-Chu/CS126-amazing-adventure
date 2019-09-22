import java.io.IOException;
import java.net.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class Input {

    public String processInput(String input) throws IOException{

        String json;

        if(isValidPath(input)) {

            String path[] = input.split("\\\\");
            int firstPath = 0;
            int secondPath = 1;
            json = Data.getFileContents(path[firstPath],path[secondPath]);
            if(isValidJson(json)) {
                return json;
            }
        }else if(isValidURL(input)) {

            json = URLConverter.getJson(input);
            if(isValidJson(json)) {
                return json;
            }
        }

        return null;
    }

    public boolean isValidURL(String toCheck) {

        try{
            URL url = new URL(toCheck);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            return true;
        }catch(MalformedURLException e) {

            System.err.println("The URL is not in a valid form");
            return false;
        }catch(IOException e) {

            System.err.println("The connection couldn't be established");
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

            System.err.println("The file path is invalid");
            return false;
        }
    }

    public boolean isValidJson(String json) {

        Gson gson = new Gson();
        try {

            gson.fromJson(json, Object.class);
           // Layout layout = gson.fromJson(json, Layout.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException e) {

            System.err.println("The form of Json is invalid");
            return false;
        }catch(com.google.gson.JsonParseException e) {

            System.err.println("The schema of Json is invalid");
            return false;
        }
    }
}
