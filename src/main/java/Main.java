

import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        List<Course> courses = new ArrayList<Course>();

        for (int i = 883; i <= 968; i++) {
            String url = "http://www.westerncalendar.uwo.ca/2016/pg" + String.valueOf(i) + ".html";

            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (Exception ex) {
                continue;
            }


            Elements divs = doc.getElementById("_ctl0__ctl0_pageContent").getElementsByClass("item-container");

            for (int z = 1; z < divs.size(); z++) {
                Elements classElements  = divs.get(z).getElementsByClass("course-name");

                String code = "";
                String name = "";
                String description = "";


                if (classElements.size() == 0) {
                    continue;
                }

                for (int j = 0; j < 5; j++) {
                    if (j < 3) {
                        code += classElements.get(j).html() + " ";
                    } else if (j == 3) {
                        name = classElements.get(j).html();
                    } else {
                        description = classElements.get(j).html();
                    }
                }

                int difference = 0;
                if (description.contains("<")) {
                    StringBuilder b = new StringBuilder(description);

                    boolean triggered = false;
                    for (int x = 0; x < description.length(); x++) {
                        if (triggered) {
                            if (b.charAt(x - difference) == '>') {
                                triggered = false;
                            }
                            b.deleteCharAt(x - difference);
                            difference++;
                        } else if (b.charAt(x - difference) == '<') {
                            triggered = true;
                            b.deleteCharAt(x - difference);
                            difference++;
                        }

                    }

                    description = b.toString();
                    description = description.replaceAll("\n", "");
                }

                code = code.replaceAll("\\/", "/");

                Course temp = new Course(code, name, description);
                courses.add(temp);
            }

        }

        mapper.writeValue(new File("master.json"), courses);
    }
}
