import java.io.IOException;
import java.net.*;


public class Input {

    public boolean validateURL(String toCheck) {

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
}
