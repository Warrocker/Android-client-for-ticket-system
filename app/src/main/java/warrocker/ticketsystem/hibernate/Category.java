package warrocker.ticketsystem.hibernate;

import java.io.Serializable;


public class Category implements Serializable {
    private int id;
    private String caption;

    public Category() {
    }

    public Category(int id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
