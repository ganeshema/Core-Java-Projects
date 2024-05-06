package productmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cp {
    static Connection con;
    public static Connection createC() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
          con= DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagementsystem", "root", "mypassword");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
