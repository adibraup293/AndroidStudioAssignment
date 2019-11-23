package com.example.assignment.Admin;

import com.example.assignment.User.User;

public class UserAdmin extends User {
    private int staffID;

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
