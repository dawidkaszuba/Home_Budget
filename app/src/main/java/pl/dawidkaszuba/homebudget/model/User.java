package pl.dawidkaszuba.homebudget.model;

public class User {

    private String username;
    private String password;

    public User(final String userName, final String password) {
        this.username = userName;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(final String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
