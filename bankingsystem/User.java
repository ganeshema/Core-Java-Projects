package bankingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private final Connection connection;
    private final Scanner scanner;

    public User(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void register() {
        scanner.nextLine();
        System.out.println("Full Name:");
        String full_name = scanner.nextLine();
        System.out.println("Email:");
        String email = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        if (user_exist(email)) {
            System.out.println("User already exits for this email address ! ");
            return;
        }
        String register_query = "insert into user(full_name,email,password) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(register_query);
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Registration Successfully Completed");
            } else {
                System.out.println("Registration failed !!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String login(){
        scanner.nextLine();
        System.out.println("Email:");
        String email=scanner.nextLine();
        System.out.println("Password");
        String password=scanner.nextLine();
        String login_query="select * from user where email=? and password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(login_query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet set=preparedStatement.executeQuery();
            if(set.next()){
                return email;
            }else{
                System.out.println("Incorrect Email or password. Please check and try again.");
                return null;
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public boolean user_exist(String email){
        String str="select * from user where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1,email);
            ResultSet set=preparedStatement.executeQuery();
            if(set.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


