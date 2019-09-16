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
        boolean status = true;

        System.out.println(layout.getStartingRoom().get);

        do{
            
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
}
