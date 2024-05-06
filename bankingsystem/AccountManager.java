package bankingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private Connection connection;
    private Scanner scanner;

    public AccountManager(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void debit_money(long account_number) {
        scanner.nextLine();
        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Security_pin: ");
        String security_pin = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (account_number != 0) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from account where account_number=? and security_pin=?");
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2, security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double current_balance = resultSet.getDouble("balance");
                    if (current_balance >= amount) {
                        preparedStatement = connection.prepareStatement("update account set balance = balance-? where account_number=?");
                        preparedStatement.setDouble(1, amount);
                        preparedStatement.setLong(2, account_number);
                        int affectedRows = preparedStatement.executeUpdate();
                        if (affectedRows > 0) {
                            System.out.println("$ " + amount + " " + "deducted from Account Number " + account_number);
                            connection.commit();
                            connection.setAutoCommit(true);
                        } else {
                            System.out.println("Transaction failed !! ");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    } else {
                        System.out.println("Insufficient Balance");
                    }
                }
            } else {
                System.out.println("Invalid Pin !!");
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void credit_money(long account_number) {
        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Security_pin: ");
        String security_pin = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (account_number != 0) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from account where account_number=? and security_pin=?");
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2, security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double current_balance = resultSet.getDouble("balance");
                    preparedStatement = connection.prepareStatement("update account set balance = balance+? where account_number=?");
                    preparedStatement.setDouble(1, amount);
                    preparedStatement.setLong(2, account_number);
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("$ " + amount + " " + "credited on Account Number " + account_number);
                        connection.commit();
                        connection.setAutoCommit(true);
                    } else {
                        System.out.println("Transaction failed !! ");
                        connection.rollback();
                        connection.setAutoCommit(true);
                    }
                }
            } else {
                System.out.println("Invalid Pin !!");
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void getBalance(long account_number) {
        scanner.nextLine();
        System.out.println("Enter the security pin :");
        String security_pin = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select balance from account where account_number=? and security_pin=?");
            preparedStatement.setLong(1, account_number);
            preparedStatement.setString(2, security_pin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double available_balance = resultSet.getDouble("balance");
                System.out.println("Your current available balance is $ : " + available_balance);
            } else {
                System.out.println("Invalid Pin");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void transfer_money(long sender_account_number) {
        System.out.println("Enter Receiver's account number: ");
        long receiver_account_number = scanner.nextLong();
        System.out.println("Enter the amount to be sent");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the security pin: ");
        String security_pin = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (sender_account_number != 0 && receiver_account_number != 0) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from account where account_number=? and security_pin=?");
                preparedStatement.setLong(1, sender_account_number);
                preparedStatement.setString(2, security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double current_balance = resultSet.getDouble("balance");
                    if (current_balance >= amount) {
                        PreparedStatement debitPreparedStatement = connection.prepareStatement("update account set balance=balance-? where account_number=?");
                        PreparedStatement creditPreparedStatement = connection.prepareStatement("update account set balance=balance+? where account_number=?");
                        creditPreparedStatement.setDouble(1, amount);
                        creditPreparedStatement.setLong(2, receiver_account_number);
                        debitPreparedStatement.setDouble(1, amount);
                        debitPreparedStatement.setLong(2, sender_account_number);
                        int rowAffected1 = debitPreparedStatement.executeUpdate();
                        int rowAffected2 = creditPreparedStatement.executeUpdate();
                        if (rowAffected1 > 0 && rowAffected2 > 0) {
                            System.out.println("Transaction successful !");
                            System.out.println("$ " + amount + " Transferred successfully !");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("Transaction failed !");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("Insufficient balance !");
                    }
                    } else {
                        System.out.println("Invalid Security pin ! ");
                    }

                connection.setAutoCommit(true);
            }
    } catch(SQLException e){
        throw new RuntimeException(e);
    }

    }
}



