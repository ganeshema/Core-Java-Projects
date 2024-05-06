package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Doctor {
    private Connection connection;


    public Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctor() {
        String query = "select * from doctor";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Available Doctors : ");
            System.out.println("+------------+--------------------+------------------------+");
            System.out.println("|     ID     |        Name        |     Specialization     |");
            System.out.println("+------------+--------------------+------------------------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization=resultSet.getString("specialization");
                System.out.printf("|%-12s|%-20s|%-20s|\n",id,name,specialization);
                System.out.println("+------------+--------------------+------------------------+");

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public boolean getPatientById(int id){
        String query="select * from doctor where id= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
            else
                return false;
        }catch (SQLException e){
            System.out.println(e);
        }

        return false;
    }
}




