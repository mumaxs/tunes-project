package com.example.demo.repository;

import com.example.demo.models.Customer;
import com.example.demo.models.CustomerCountry;
import com.example.demo.models.CustomerGenre;
import com.example.demo.models.CustomerSpender;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {

    public ArrayList<Customer> getAllCustomersFromDB() {
        ArrayList<Customer> customerList = new ArrayList<Customer>();

        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Customer");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                customerList.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Email")

                ));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    public Customer getSpecificCustomerFromDB(int id) {
        Customer customer = new Customer();

        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Customer where CustomerId=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Email"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;

    }

    public ArrayList<Customer> getSpecificCustomerByNameFromDB(String name) {
        ArrayList<Customer> customerList = new ArrayList<>();

        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Customer where FirstName LIKE ?");
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                customerList.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Email")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    public ArrayList<Customer> getCustomerPageFromDB(int limit, int offset) {
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Customer LIMIT ? offset ?");
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                customerArrayList.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Email")

                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerArrayList;
    }

    public void addCustomer(Customer customer) {
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Customer ('FirstName', 'LastName', 'Country', 'PostalCode', 'Phone', 'Email') VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer, int id) {
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Customer SET FirstName = ?, LastName = ?, Country = ?, PostalCode = ?, Phone = ?, Email = ? WHERE CustomerID = ?");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<CustomerCountry> getCustomersPerCountry() {
        ArrayList<CustomerCountry> countryList = new ArrayList<CustomerCountry>();
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Country, count() FROM Customer GROUP BY Country ORDER BY count() DESC");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                countryList.add(new CustomerCountry(
                        rs.getString("Country"),
                        rs.getInt("count()")));
                System.out.println(rs.getString("Country") + " " + rs.getInt("count()"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return countryList;
    }

    public ArrayList<CustomerSpender> getCustomerSpentList() {
        ArrayList<CustomerSpender> spenderList = new ArrayList<CustomerSpender>();
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT distinct FirstName, Total FROM Customer, Invoice WHERE Customer.customerID = Invoice.CustomerId ORDER BY Total DESC");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                spenderList.add(new CustomerSpender(
                        rs.getString("FirstName"),
                        rs.getDouble("Total")));
                System.out.println(rs.getString("FirstName") + " " + rs.getDouble("Total"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return spenderList;
    }

    public ArrayList<CustomerGenre> getCustomerMostPopularGenreList(int customerID) {
        ArrayList<CustomerGenre> genreList = new ArrayList<CustomerGenre>();
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT FirstName, Genre.Name, count(Genre.Name) as GenreCount\n" +
                    "FROM Customer, Genre, Invoice, InvoiceLine, Track\n" +
                    "WHERE Customer.CustomerId= Invoice.CustomerId\n" +
                    "  and Invoice.InvoiceId=InvoiceLine.InvoiceId\n" +
                    "  and InvoiceLine.TrackId= Track.TrackId\n" +
                    "  and Genre.GenreId=Track.GenreId\n" +
                    "  and Invoice.CustomerId=?\n" +
                    "GROUP BY Genre.GenreId\n" +
                    "ORDER BY GenreCount DESC;");
            preparedStatement.setInt(1, customerID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                genreList.add(new CustomerGenre(
                        rs.getString("FirstName"),
                        rs.getString("Name"),
                        rs.getInt("GenreCount")));
                System.out.println(rs.getString("FirstName") + " " + rs.getString("Name") + " " + rs.getInt("GenreCount"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ArrayList<CustomerGenre> topGenre = new ArrayList<CustomerGenre>();
        topGenre.add(genreList.get(0));
        for (int i = 0; i < genreList.size(); i++) {
            if (genreList.get(i).getGenreCount() == genreList.get(i + 1).getGenreCount()) {
                topGenre.add(genreList.get(i + 1));
            } else {
                break;
            }
        }
        topGenre.forEach(genre -> {
            System.out.println(genre.getGenreCount());
        });
        return topGenre;
    }
}
