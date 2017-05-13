package ipc.am.lab_20.db.entity;

/**
 * Created by Gevorg on 5/8/2017.
 */

public class User {
    private long id;
    private String name;
    private String login;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "name " + name + ", login " + login + ", password " + password;
    }

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }
}
