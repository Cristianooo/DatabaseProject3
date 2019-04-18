import java.sql.*;

public class DatabaseConnection {
    static Connection getdatabaseconnection()

    {
        try {
            return DriverManager.getConnection("jdbc:mysql://35.233.241.135:3306/TV_Orders?useSSL=false","test", "password");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
