package oppa.example.midtermproject.model;

import java.util.Date;

public class NoteRecord {

    private String title;
    private String content;
    private Date time;

    //--------------------------------------------
    public NoteRecord(String title, String content, Date time) {
        this.title = title;
        this.content = content;
        this.time = time;
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
