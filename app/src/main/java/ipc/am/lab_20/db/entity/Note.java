package ipc.am.lab_20.db.entity;

public class Note {
    private long id;
    private String text;
    private String time;
    private String date;
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Note(){}

    public Note(String text, String time, String date, User user) {
        this.text = text;
        this.time = time;
        this.date = date;
        this.user = user;
    }

    @Override
    public String toString() {
        return text + " , time='" + time + '\'' + ", date='" + date + '\'';
    }
}
