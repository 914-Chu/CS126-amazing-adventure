import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class URLConverter {

    private String url;

    public URLConverter(String url) {

        url = "https://courses.grainger.illinois.edu/cs126/fa2019/assignments/siebel.json";
    }

    public String getJson() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please insert the URL: ");
        url = reader.readLine();
        String content =  URLReader(new URL(url));
        return content;
    }

    // Code below derived from:
    // https://www.techiedelight.com/read-contents-of-url-into-string-java/

    public String URLReader(URL url) throws IOException {
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
