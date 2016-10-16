/**
 * Created by dane on 10/15/16.
 */
public class Course {

    private String code;
    private String name;
    private String description;

    public String getDescription () {
        return this.description;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Course () {
        code = null;
        name = null;
        description = null;
    }

    public Course(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public void print () {
        System.out.println(code + " " + name + " - " + description);
    }


}
