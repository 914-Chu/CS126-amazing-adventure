import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;


public class URLConverter {

    public static String getJson() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please insert the URL: ");
        String url = reader.readLine();

        if(validateURL(url)) {

            return URLReader(new URL(url));
        }else {

            return null;
        }
    }

    public static String getJson(String inputURL) throws IOException{

        if(validateURL(inputURL)) {

            return URLReader(new URL(inputURL));
        }else {

            return null;
        }
    }

    // Code below derived from:
    // https://www.techiedelight.com/read-contents-of-url-into-string-java/

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

    private static boolean validateURL(String toCheck) {

        try{
            URI url = new URL(toCheck).toURI();
            return true;
        }catch(MalformedURLException e) {
            System.out.println("Invalid URL");
            return false;
        }catch(URISyntaxException e) {
            System.out.println("Invalid URL");
            return false;
        }
    }

}
