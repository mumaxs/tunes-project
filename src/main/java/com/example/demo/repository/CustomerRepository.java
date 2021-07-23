package com.example.demo.repository;

import com.example.demo.models.Customer;
import com.example.demo.models.CustomerCountry;
import com.example.demo.models.CustomerGenre;
import com.example.demo.models.CustomerSpender;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {
    private String URL= ConnectionHelper.DATABASE_CONNECTION_URL;
    private Connection conn=null;

    /**
     *
     * @return The method returns a customerArrayList consisted of all the customers in the DB.
     * The customer object has attributes:
     * 1)Customer ID
     * 2)First name
     * 3)Last name
     * 4)Country of the customer
     * 5)Postal code
     * 6)Phone number
     * 7)Email address
     */
    public ArrayList<Customer> getAllCustomersFromDB() {
        ArrayList<Customer> customerList = new ArrayList<Customer>();


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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return customerList;
    }


    /**
     * @param id is an integer used to search for a specific customer in the DB.
     * @return The method returns a customer object based on the customers ID.
     * The customer object has attributes:
     * 1)Customer ID
     * 2)First name
     * 3)Last name
     * 4)Country of the customer
     * 5)Postal code
     * 6)Phone number
     * 7)Email address
     */
    public Customer getSpecificCustomerFromDB(int id) {
        Customer customer = new Customer();


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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return customer;

    }

    /**
     * @param name is a string used to search through all first names who match with the input.
     * @return The method returns a customerArrayList consisted of all the customers who's first name have at least a partial match with the input string.
     * A more specific input will result i a more narrow search result.
     * The customer object has attributes:
     * 1)Customer ID
     * 2)First name
     * 3)Last name
     * 4)Country of the customer
     * 5)Postal code
     * 6)Phone number
     * 7)Email address
     */
    public ArrayList<Customer> getCustomersBySearchWordFromDB(String name) {
        ArrayList<Customer> customerList = new ArrayList<>();


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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return customerList;
    }

    /**
     * @param limit is an integer used to specify the starting row of the search.
     * @param offset is an integer used to specify the length of the search by rows.
     * @return The method returns a customerArrayList consisted of all the customers that are inside the spectrum of the starting row and the amount of rows the method will search through.
     * The customer object has attributes:
     * 1)Customer ID
     * 2)First name
     * 3)Last name
     * 4)Country of the customer
     * 5)Postal code
     * 6)Phone number
     * 7)Email address
     */
    public ArrayList<Customer> getCustomerPageFromDB(int limit, int offset) {
        ArrayList<Customer> customerArrayList = new ArrayList<>();
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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return customerArrayList;
    }

    /**
     *
     * @param customer is the customer object being inserted into the customer table in the DB.
     * The customer ID attribute is generated automatically by the DB.
     * The customer attributes being inserted are:
     * 1)First name
     * 2)Last name
     * 3)Country of the customer
     * 4)Postal code
     * 5)Phone number
     * 6)Email address
     */
    public void addCustomer(Customer customer) {
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

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     *
     * @param customer is the object used to update the data related to the specific row in the customer table.
     * @param id is the integer used to specify which row in the customer table is being updated.
     * The customer attributes being updated are:
     * 1)First name
     * 2)Last name
     * 3)Country of the customer
     * 4)Postal code
     * 5)Phone number
     * 6)Email address
     */
    public void updateCustomer(Customer customer, int id) {

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

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     *
     * @return the method returns a customerCountryArrayList consisted of customerCountry objects that are ordered by a descending order of customers per country
     * The object have the attributes country name and the amount of customers related to that specific country
     */
    public ArrayList<CustomerCountry> getCustomersPerCountry() {
        ArrayList<CustomerCountry> countryList = new ArrayList<CustomerCountry>();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Country, count() FROM Customer GROUP BY Country ORDER BY count() DESC");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                countryList.add(new CustomerCountry(
                        rs.getString("Country"),
                        rs.getInt("count()")));
/*
                System.out.println(rs.getString("Country") + " " + rs.getInt("count()"));
*/
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


        return countryList;
    }

    /**
     *
     * @return a customerSpenderArrayList consisted of customerSpender objects with the attributes: customers first name and the total amount spent on purchasing albums.
     * The order is based on descending amount spent.
     */
    public ArrayList<CustomerSpender> getCustomerSpentList() {
        ArrayList<CustomerSpender> spenderList = new ArrayList<CustomerSpender>();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT distinct FirstName, Total FROM Customer, Invoice WHERE Customer.customerID = Invoice.CustomerId ORDER BY Total DESC");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                spenderList.add(new CustomerSpender(
                        rs.getString("FirstName"),
                        rs.getDouble("Total")));
/*
                System.out.println(rs.getString("FirstName") + " " + rs.getDouble("Total"));
*/
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


        return spenderList;
    }

    /**
     *
     * @param customerID is used to specify which customer the search is based on
     * @return a CustomerGenreArrayList is consisted of a customerGenre object
     * Every object in the arraylist consist of three attributes:
     * 1) Name of the customer
     * 2) Name of the genre
     * 3) The amount of tracks related to the specific genre
     */
    public ArrayList<CustomerGenre> getCustomerMostPopularGenreList(int customerID) {
        ArrayList<CustomerGenre> genreList = new ArrayList<CustomerGenre>();

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
/*
                System.out.println(rs.getString("FirstName") + " " + rs.getString("Name") + " " + rs.getInt("GenreCount"));
*/
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        /**
         * The code bellow copies all the object with the highest amount of tracks related to the genre that also are of the same amount.
         * This is to remove all the none favourites and add potentially numerous favourites in the case of a tie.
         */
        ArrayList<CustomerGenre> topGenre = new ArrayList<>();
        topGenre.add(genreList.get(0));
        for (int i = 0; i < genreList.size(); i++) {
            if (genreList.get(i).getGenreCount() == genreList.get(i + 1).getGenreCount()) {
                topGenre.add(genreList.get(i + 1));
            } else {
                break;
            }
        }
        /*topGenre.forEach(genre -> {
            System.out.println(genre.getGenreCount());
        });*/
        return topGenre;
    }
}
