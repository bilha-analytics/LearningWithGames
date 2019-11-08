public class GameItem {
    private  String name = null;
    private String description = null;
    private int iconID =  -99;

    public GameItem(String name, String description, int iconID){
        this.name = name;
        this.description = description;
        this.iconID = iconID;
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

}
