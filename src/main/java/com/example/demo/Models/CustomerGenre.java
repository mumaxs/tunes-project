package com.example.demo.Models;

public class CustomerGenre {
    private String genreName;
    private String customerName;
    private int genreCount;

    public CustomerGenre(String genreName, String customerName, int genreCount) {
        this.genreName = genreName;
        this.customerName = customerName;
        this.genreCount = genreCount;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getGenreCount() {
        return genreCount;
    }
}
