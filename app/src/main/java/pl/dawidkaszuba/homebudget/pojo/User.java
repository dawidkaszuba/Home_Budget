package pl.dawidkaszuba.homebudget.pojo;

public class User {

    private Long userId;
    private String username;
    private String password;

    public User(final String userName, final String password) {
        this.username = userName;
        this.password = password;
    }

    public User(Long userId) {
    }

    public User(Long userId, String userName) {
        this.userId = userId;
        this.username = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
