package warrocker.ticketsystem.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Status  implements Serializable {
    private int status;
    private String caption;
    private String ucolor;
    public Status(){}
    public Status(int status, String caption, String ucolor) {
        this.status = status;
        this.caption = caption;
        this.ucolor = ucolor;
    }

    int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUcolor() {
        return ucolor;
    }

    public void setUcolor(String ucolor) {
        this.ucolor = ucolor;
    }

}
