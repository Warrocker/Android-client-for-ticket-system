package warrocker.ticketsystem;


import java.io.Serializable;

class RequestCode implements Serializable {
    //Коды запросов по статус заявки
    static final int ALL = -1;
    static final int ACTIVE = -2;
    public static final int FREEZED = -3;
    public static final int LATE = -4;


    //Коды запросов для вызова сопутсвующей информации
    static final int CATEGORY = -11;
    static final int STATUS = -12;
    static final int USERS = -13;
    static final int PRIORITY = -14;
    static final int GROUP = -15;

    private int code;
    public RequestCode(int code){
        this.code = code;
    }
    int getCode() {
        return code;
    }

    void setCode(int code) {
        this.code = code;
    }
}
