package com.example.assignment.Admin;

public class UserAdmin {
    private int staffID;
    private String name;
    private String username;
    private String password;

    public UserAdmin() {
    }

    public UserAdmin(int staffID, String name, String username, String password) {
        this.staffID = staffID;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public UserAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "UserAdmin{" +
                "staffID=" + staffID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
