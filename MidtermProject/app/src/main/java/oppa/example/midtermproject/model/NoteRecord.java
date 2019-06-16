package oppa.example.midtermproject.model;

import java.util.Date;

public class NoteRecord {

    private String title;
    private String content;
    private Date time;
    private String email;
    private String phone;

    //--------------------------------------------
    public NoteRecord(String title, String content, String email, String phone, Date time) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.email = email;
        this.phone = phone;
    }
    //--------------------------------------------
    public String getContent() {

        return this.content;
    }
    //--------------------------------------------
    public Date getTime() {

        return this.time;
    }
    //--------------------------------------------
    public String getTitle() {

        return this.title;
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
    }
    //--------------------------------------------
    public void setContent(String content) {
        this.content = content;
    }
    //--------------------------------------------
    public void setTime(Date time) {
        this.time = time;
    }
}
