package com.example.demo.models;

public class CustomerSpender {
    private String firstName;
    private double spent;


    public CustomerSpender(String firstName, double spent) {
        this.firstName = firstName;
        this.spent = spent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getSpent() {
        return spent;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }
}
