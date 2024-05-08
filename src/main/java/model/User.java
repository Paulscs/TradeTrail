package model;

    public class User {
        private int id;
        private String username;
        private String email;
        private String password;
        private String mobile;
        private final boolean admin;
    // Constructors, getters, and setters


    public User(String username, String email, String password, String mobile, boolean admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.admin = admin;
    }

    public User(int id, String username, String email, String password, String mobile, boolean admin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.admin = admin;
    }

    // Getters and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isAdmin() {
        return admin;
    }

}
