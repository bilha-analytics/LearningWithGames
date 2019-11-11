import java.io.Serializable;

public class GameItem implements Serializable {
    private int id = -1;
    private  String name = null;
    private String description = null;
    private int iconID =  -99;
    private String question_tag = null;


    public static int BIG_NUMBER_GAME = 1;
    public static int BIG_LETTER_GAME = 2;
    public static int CAPITAL_LETTER_GAME = 3;
    public static int WORD_GAME = 4;
    public static int COUNTING_GAME = 5;
    public static int ANIMALS_GAME = 6;




    public GameItem(int id, String name, String description, int iconID, String question_tag){
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.iconID = iconID;
        this.question_tag = question_tag;
    }


    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }
}
