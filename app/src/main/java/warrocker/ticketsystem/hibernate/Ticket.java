package warrocker.ticketsystem.hibernate;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;


public class Ticket  implements Serializable {
    private int id;
    private int user_id;
    private Timestamp cr_dtime;
    private Timestamp st_dtime;
    private Timestamp ch_dtime;
    private String title;
    private String descr;
    private int status;
    private int category;
    private int priority;
    private int performer;
    private int grup_per;

    public Ticket() {
    }

    public Ticket(int id, int user_id, Timestamp cr_dtime, Timestamp st_dtime, Timestamp ch_dtime, String title, String descr, int status, int category, int priority, int performer, int grup_per) {
        this.id = id;
        this.user_id = user_id;
        this.cr_dtime = cr_dtime;
        this.st_dtime = st_dtime;
        this.ch_dtime = ch_dtime;
        this.title = title;
        this.descr = descr;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.performer = performer;
        this.grup_per = grup_per;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCr_dtime() {
        return cr_dtime;
    }

    public void setCr_dtime(Timestamp cr_dtime) {
        this.cr_dtime = cr_dtime;
    }

    public Date getSt_dtime() {
        return st_dtime;
    }

    public void setSt_dtime(Timestamp st_dtime) {
        this.st_dtime = st_dtime;
    }

    public Date getCh_dtime() {
        return ch_dtime;
    }

    public void setCh_dtime(Timestamp ch_dtime) {
        this.ch_dtime = ch_dtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }



    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPerformer() {
        return performer;
    }

    public void setPerformer(int performer) {
        this.performer = performer;
    }

    public int getGrup_per() {
        return grup_per;
    }

    public void setGrup_per(int grup_per) {
        this.grup_per = grup_per;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
