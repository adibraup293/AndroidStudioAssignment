package com.example.assignment.Unit;

public class Unit {
    private int unitID;
    private String Availability;

    public Unit() {
    }

    public Unit(int unitID, String availability) {
        this.unitID = unitID;
        Availability = availability;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitID=" + unitID +
                ", Availability='" + Availability + '\'' +
                '}';
    }
}
