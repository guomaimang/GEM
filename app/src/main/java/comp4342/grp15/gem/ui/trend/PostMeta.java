package comp4342.grp15.gem.ui.trend;

import java.util.Random;

public class PostMeta {
    private int id;
    private String posterName;
    private String message;

    public PostMeta(int id, String posterName, String message){
        this.id = id;
        this.posterName = posterName;
        this.message = message;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPosterName() {
        return posterName;
    }
    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args){

    }
}
