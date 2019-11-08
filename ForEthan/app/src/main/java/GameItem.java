import java.io.Serializable;

public class GameItem implements Serializable {
    private  String name = null;
    private String description = null;
    private int iconID =  -99;
    private String question_tag = null;

    public GameItem(String name, String description, int iconID, String question_tag){
        super();
        this.name = name;
        this.description = description;
        this.iconID = iconID;
        this.question_tag = question_tag;
    }


    public String getQuestion_tag() {
        return question_tag;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getIconID() {
        return iconID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public void setQuestion_tag(String question_tag) {
        this.question_tag = question_tag;
    }
}
