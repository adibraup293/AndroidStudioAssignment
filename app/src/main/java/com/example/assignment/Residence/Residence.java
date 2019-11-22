package com.example.assignment.Residence;

public class Residence {
    private int residenceID;
    private String address;
    private int numOfUnits;
    private int sizePerUnit;
    private double monthlyRental;

    public Residence() {
    }

    public Residence(int residenceID, String address, int numOfUnits, int sizePerUnit, double monthlyRental) {
        this.residenceID = residenceID;
        this.address = address;
        this.numOfUnits = numOfUnits;
        this.sizePerUnit = sizePerUnit;
        this.monthlyRental = monthlyRental;
    }

    public int getResidenceID() {
        return residenceID;
    }

    public void setResidenceID(int residenceID) {
        this.residenceID = residenceID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumOfUnits() {
        return numOfUnits;
    }

    public void setNumOfUnits(int numOfUnits) {
        this.numOfUnits = numOfUnits;
    }

    public int getSizePerUnit() {
        return sizePerUnit;
    }

    public void setSizePerUnit(int sizePerUnit) {
        this.sizePerUnit = sizePerUnit;
    }

    public double getMonthlyRental() {
        return monthlyRental;
    }

    public void setMonthlyRental(double monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    @Override
    public String toString() {
        return "Residence{" +
                "residenceID=" + residenceID +
                ", address='" + address + '\'' +
                ", numOfUnits=" + numOfUnits +
                ", sizePerUnit in sq ft.= " + sizePerUnit +
                ", monthlyRental=" + monthlyRental +
                '}';
    }
}
