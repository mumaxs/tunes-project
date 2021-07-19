import java.sql.*;

public class Main {


    public static void main(String[] args) {
         String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
         Connection conn=null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Customer");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
