package com.example.assignment.Admin;

import com.example.assignment.User.User;

public class UserAdmin extends User {
    private int usertype = 0;
    private int staffID;

    public UserAdmin() {
    }

    public UserAdmin(int staffID) {
        this.staffID = staffID;
    }

    @Override
    public int getUsertype() {
        return usertype;
    }

    @Override
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    @Override
    public String toString() {
        return "UserAdmin{" +
                "staffID=" + staffID +
                '}';
    }
}
