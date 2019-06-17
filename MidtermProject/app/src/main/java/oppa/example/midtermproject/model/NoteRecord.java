package oppa.example.midtermproject.model;

import java.util.Date;

public class NoteRecord {

    private int id;
    private String title;
    private String content;
    private String time;
    private String email;
    private String phone;
    private boolean state;

    //--------------------------------------------
    public NoteRecord(int id, String title, String content, String email, String phone, String time, boolean state) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.email = email;
        this.phone = phone;
        this.state = state;
    }
    //--------------------------------------------
    public int getId() {

        return this.id;
    }
    //--------------------------------------------
    public String getContent() {

        return this.content;
    }

    //--------------------------------------------
    public String getTime() {

        return this.time;
    }

    //--------------------------------------------
    public String getTitle() {

        return this.title;
    }
    //--------------------------------------------
    public boolean getState() {

        return this.state;
    }

    //--------------------------------------------
    public String getEmail() {

        return this.email;
    }

    //--------------------------------------------
    public String getPhone() {

        return this.phone;
    }

    //--------------------------------------------
    public void setContent(String content) {
        this.content = content;
    }

    //--------------------------------------------
    public void setTime(String time) {
        this.time = time;
    }

    //--------------------------------------------
    public void setTaskName(String taskname) {
        this.title = taskname;
    }
    //--------------------------------------------
    public void setState(boolean state) {
        this.state = state;
    }

    //--------------------------------------------
    public void setPhone(String phone) {
        this.phone = phone;
    }

    //--------------------------------------------
    public void setEmail(String email) {
        this.email = email;
    }

    /*--------------------------------------------
    public String getDay() {

        int index_day = this.time.getDay();
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        return days[index_day];
    }

    //--------------------------------------------
    public String getMonth() {

        int index_month = this.time.getMonth();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return months[index_month];
    }*/
}
