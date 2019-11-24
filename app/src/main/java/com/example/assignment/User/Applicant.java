package com.example.assignment.User;

public class Applicant extends User{
    private int usertype = 1;
    private String email;
    private Double monthlyIncome;

    public Applicant(String username, int usertype, String password, String name, String email, Double monthlyIncome) {
        super(username, usertype, password, name);
        this.email = email;
        this.monthlyIncome = monthlyIncome;
    }

    public Applicant() {
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

    @Override
    public int getUsertype() {
        return usertype;
    }

    @Override
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "email='" + email + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                '}';
    }
}
