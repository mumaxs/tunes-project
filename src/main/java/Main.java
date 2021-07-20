import com.example.demo.Models.Customer;
import com.example.demo.Store;

import java.sql.*;

public class Main {


    public static void main(String[] args) {
      /*   String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
         Connection conn=null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Customer");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("CustomerId") + " "  + rs.getString("FirstName")  + " "  + rs.getString("LastName") + " "  + rs.getString("country")
                        + " "  + rs.getString("PostalCode") + " "  + rs.getString("Phone")  + " "  +rs.getString("Email")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        Store store= new Store();
        /*Customer mackan = new Customer("Marucs", "TL", "Sweden", "39244", "mtl@kek.se", "123456");
        System.out.println(store.getSpecificCustomerFromDB(1));
        System.out.println( store.getSpecificCustomerByNameFromDB("a"));
        store.addCustomer(mackan);
        System.out.println("Addededded");
        mackan.setFirstName("Marcus");
        store.updateCustomer(mackan, 60);*/
        //store.getCustomersPerCountry();
        store.getCustomerSpentList();
        store.getCustomerMostPopularGenreList(12);


    }


}
