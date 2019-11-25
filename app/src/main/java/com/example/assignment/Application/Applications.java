package com.example.assignment.Application;

import java.util.Date;

public class Applications {
    private int applicationID ;
    private String applicationDate;
    private String requiredMonth;
    private int requiredYear;
    private String status;

    public Applications() {
    }

    public Applications(String applicationDate, String requiredMonth, int requiredYear, String status, int resID) {
        this.applicationDate = applicationDate;
        this.requiredMonth = requiredMonth;
        this.requiredYear = requiredYear;
        this.status = status;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getRequiredMonth() {
        return requiredMonth;
    }

    public void setRequiredMonth(String requiredMonth) {
        this.requiredMonth = requiredMonth;
    }

    public int getRequiredYear() {
        return requiredYear;
    }

    public void setRequiredYear(int requiredYear) {
        this.requiredYear = requiredYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Applications{" +
                "applicationID=" + applicationID +
                ", applicationDate=" + applicationDate +
                ", requiredMonth='" + requiredMonth + '\'' +
                ", requiredYear=" + requiredYear +
                ", status='" + status + '\'' +
                '}';
    }
}
