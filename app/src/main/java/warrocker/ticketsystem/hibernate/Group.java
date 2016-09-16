package warrocker.ticketsystem.hibernate;

import java.io.Serializable;


public class Group implements Serializable {
    private int id;
    private String caption;
    private int rule;
    public Group(){}
    public Group(int id, String caption, int rule) {
        this.id = id;
        this.caption = caption;
        this.rule = rule;
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

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }
}
