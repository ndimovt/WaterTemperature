package io.github.ndimovt;

public class Scientist {
    private String userName;
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Scientist(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
