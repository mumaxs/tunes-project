package com.example.demo.models;

public class CustomerCountry {
    private String countryName;
    private int numberOfCustomers;

    public CustomerCountry(String countryName, int numberOfCustomers) {
        this.countryName = countryName;
        this.numberOfCustomers = numberOfCustomers;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }
}
