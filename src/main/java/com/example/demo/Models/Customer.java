package com.example.demo.Models;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String country;
    private String postalCode;
    private String email;
    private String phone;

    public Customer(String firstName, String lastName, String country, String postalCode, String email, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.postalCode = postalCode;
        this.email = email;
        this.phone = phone;
    }
    public Customer(int customerID, String firstName, String lastName, String country, String postalCode, String email, String phone){
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.postalCode = postalCode;
        this.email = email;
        this.phone = phone;
    }
    public Customer(){

    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public int getCustomerID(){
        return this.customerID;
    }
    public String getCountry(){
        return this.country;
    }
    public String getPostalCode(){
        return this.postalCode;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
