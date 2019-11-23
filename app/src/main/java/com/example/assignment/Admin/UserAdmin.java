package com.example.assignment.Admin;

import com.example.assignment.User.User;

public class UserAdmin extends User {
    private int staffID;

    @Override
    public int getUsertype() {
        return usertype;
    }

    @Override
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    private int usertype=0;

    public UserAdmin() {
    }

    public UserAdmin(int staffID) {
        this.staffID = staffID;
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
