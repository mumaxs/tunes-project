package com.example.demo;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class Store {

    public String getAllCustomersFromDB(){
        String customer = new String();

        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Customer");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString("CustomerId") + " "  + rs.getString("FirstName")  + " "  + rs.getString("LastName") + " "  + rs.getString("country")
                        + " "  + rs.getString("PostalCode") + " "  + rs.getString("Phone")  + " "  +rs.getString("Email")
                );
                customer+=" " +rs.getString("CustomerId") + " "  + rs.getString("FirstName")  + " "  + rs.getString("LastName") + " "  + rs.getString("country")
                        + " "  + rs.getString("PostalCode") + " "  + rs.getString("Phone")  + " "  +rs.getString("Email") + "\n";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public String getSpecificCustomerFromDB(int id){

        String customer = new String();

        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Customer where CustomerId=?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                customer=" " +rs.getString("CustomerId") + " "  + rs.getString("FirstName")  + " "  + rs.getString("LastName") + " "  + rs.getString("country")
                        + " "  + rs.getString("PostalCode") + " "  + rs.getString("Phone")  + " "  +rs.getString("Email");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public ArrayList<String> getSpecificCustomerByNameFromDB(String name){
        ArrayList<String> customerArrayList= new ArrayList<>();
        //customerArrayList.clear();
        String customer = new String();

        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Customer where FirstName LIKE ?");
            preparedStatement.setString(1,"%"+name+"%");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                customer=" " +rs.getString("CustomerId") + " "  + rs.getString("FirstName")  + " "  + rs.getString("LastName") + " "  + rs.getString("country")
                        + " "  + rs.getString("PostalCode") + " "  + rs.getString("Phone")  + " "  +rs.getString("Email");
                System.out.println(customer);
                customerArrayList.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerArrayList;
    }


}
