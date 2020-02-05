package pl.dawidkaszuba.homebudget.model;

public class LoggedUser {

    private String userName;
    private Long id;
    private String token;


    public LoggedUser(final String userName, final Long id, final String token) {
        this.userName = userName;
        this.id = id;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
