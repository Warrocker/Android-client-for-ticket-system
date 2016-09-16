package warrocker.ticketsystem.hibernate;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable{
    private int id;
    private String login;
    private String hash;
    private String hash5;
    private String caption;
    private String email;
    private String access;
    private Date birthday;
    private String pnumber;
    private String pnumber2;
    private int icq;
    private String home_adr;
    private String pfoto;
    private String skype;
    private String lang;
    private String ucolor;
    private double latitude;
    private double longitude;

    public Users(){}

    public Users(String login, String hash) {
        this.login = login;
        this.hash = hash;
    }

    public Users(int id, String login, String hash, String hash5, String caption, String email, String access, Date birthday, String pnumber, String pnumber2, int icq, String home_adr, String pfoto, String skype, String lang, String ucolor, double latitude, double longitude) {
        this.id = id;
        this.login = login;
        this.hash = hash;
        this.hash5 = hash5;
        this.caption = caption;
        this.email = email;
        this.access = access;
        this.birthday = birthday;
        this.pnumber = pnumber;
        this.pnumber2 = pnumber2;
        this.icq = icq;
        this.home_adr = home_adr;
        this.pfoto = pfoto;
        this.skype = skype;
        this.lang = lang;
        this.ucolor = ucolor;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    String getHash5() {
        return hash5;
    }

    public void setHash5(String hash5) {
        this.hash5 = hash5;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getPnumber2() {
        return pnumber2;
    }

    public void setPnumber2(String pnumber2) {
        this.pnumber2 = pnumber2;
    }

    public int getIcq() {
        return icq;
    }

    public void setIcq(int icq) {
        this.icq = icq;
    }

    public String getHome_adr() {
        return home_adr;
    }

    public void setHome_adr(String home_adr) {
        this.home_adr = home_adr;
    }

    public String getPfoto() {
        return pfoto;
    }

    public void setPfoto(String pfoto) {
        this.pfoto = pfoto;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUcolor() {
        return ucolor;
    }

    public void setUcolor(String ucolor) {
        this.ucolor = ucolor;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
