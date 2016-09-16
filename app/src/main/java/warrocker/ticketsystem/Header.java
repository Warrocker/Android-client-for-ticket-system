package warrocker.ticketsystem;

import java.io.Serializable;


public class Header implements Serializable {
    int length;

    public Header(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
