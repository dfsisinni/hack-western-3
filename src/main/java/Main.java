import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        for (int i = 883; i <= 968; i++) {
            String url = "http://www.westerncalendar.uwo.ca/2016/pg" + String.valueOf(i) + ".html";
            Document doc = Jsoup.connect(url).get();
        }
    }
}
