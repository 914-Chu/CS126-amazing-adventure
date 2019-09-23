import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import com.google.gson.Gson;

public class Input {

    public String processInput(String input) throws IOException{

        String json;

        if(isValidFile(input)) {

            json = Data.getFileContents("src","test", "test_resources", input);
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

    public boolean isValidFile(String filename) {
        try {

            String path = "src\\test\\test_resources\\" + filename;
            Path paths = Paths.get(path);
            if(Files.exists(paths) && !Files.isDirectory(paths)) {
                return true;
            }else {
                throw new FileNotFoundException();
            }
        } catch(InvalidPathException | FileNotFoundException e) {

            System.err.println("Couldn't find file");
            return false;
        }
    }

    public boolean isValidJson(String json) {

        Gson gson = new Gson();
        try {

            gson.fromJson(json, Layout.class);
            return true;
        } catch(com.google.gson.JsonParseException e) {

            System.err.println("The form of Json is invalid");
            return false;
        }
    }

    public static String getUserInput(List<Direction> directionList) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("From here, you can go: ");
        Output.printDirections(directionList);
        return reader.readLine().trim();
    }

//    public static boolean isValidSchema(String json) {
//
//        String defaultSchema = Data.getFileContents("src", "test", "test_resources", "defaultSchema");
//        Jsonschema schema = JsonSchema.Parse(defaultSchema);
//    }
}
