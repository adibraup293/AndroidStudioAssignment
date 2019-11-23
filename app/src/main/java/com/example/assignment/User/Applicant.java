package com.example.assignment.User;

public class Applicant extends User{
    private String email;
    private Double monthlyIncome;

    public Applicant() {

    }

    public Applicant(String name, int usertype, String username, String password, String email, Double monthlyIncome) {
        super(name, usertype, username, password);
        this.email = email;
        this.monthlyIncome = monthlyIncome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
