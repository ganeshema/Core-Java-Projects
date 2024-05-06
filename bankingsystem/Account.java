package bankingsystem;

import java.sql.*;
import java.util.Scanner;

public class Account {
    private Connection connection;
    private Scanner scanner;

    public Account(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }
    public long openAccount(String email) {
        if (!account_exist(email)) {
            String openAccount_Query = "insert into account ( account_number, full_name, email, balance, security_pin) values(?,?,?,?,?)";
            scanner.nextLine();
            System.out.println("Enter full Name:");
            String full_name = scanner.nextLine();
            System.out.println("Enter initial Amount:");
            double balance = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter Security_pin:");
            String security_pin = scanner.nextLine();
            long account_number = generateAccountNumber();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(openAccount_Query);
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2, full_name);
                preparedStatement.setString(3, email);
                preparedStatement.setDouble(4, balance);
                preparedStatement.setString(5, security_pin);
                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0) {
                    return account_number;
                } else {
                    throw new RuntimeException("Account creation failed !!");
                }
            } catch (SQLException e) {
            }
            }
            throw new RuntimeException("Account already exists !");
        }


    public boolean account_exist(String email) {
        String str = "select * from account where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1, email);
            ResultSet set = preparedStatement.executeQuery();
            if (set.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private long generateAccountNumber() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select account_number from account order by account_number desc limit 1");
            if (resultSet.next()) {
                long last_account_number = resultSet.getLong("account_number");
                return last_account_number+1;
            } else {
                return 100000100;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long getAccountNumber(String email) {
        String str = "select account_number from account where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1, email);
            ResultSet set = preparedStatement.executeQuery();
            if (set.next()) {
                return set.getLong("account_number");
            }
        } catch (SQLException e) {
        }
            throw new RuntimeException("Account does not exits with this email Id!");
        }
    }

