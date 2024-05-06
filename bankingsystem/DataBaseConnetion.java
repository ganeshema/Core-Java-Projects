package bankingsystem;

import java.sql.Connection;
import java.sql.DriverManager;

class DataBaseConnetion {

    //loading JDBC driver it should load inside the method not in class body
    public Connection jdbcConnection() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/banking_system";
            String username = "root";
            String password = "mypassword";
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;

    }
}
