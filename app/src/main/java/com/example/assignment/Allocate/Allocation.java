package com.example.assignment.Allocate;

import java.util.Date;

public class Allocation {
    private int allocateID;
    private Date fromDate;
    private String duration;
    private Date endDate;

    public Allocation() {
    }

    public Allocation(int allocateID, Date fromDate, String duration, Date endDate) {
        this.allocateID = allocateID;
        this.fromDate = fromDate;
        this.duration = duration;
        this.endDate = endDate;
    }

    public int getAllocateID() {
        return allocateID;
    }

    public void setAllocateID(int allocateID) {
        this.allocateID = allocateID;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "allocateID=" + allocateID +
                ", fromDate=" + fromDate +
                ", duration='" + duration + '\'' +
                ", endDate=" + endDate +
                '}';
    }
}
