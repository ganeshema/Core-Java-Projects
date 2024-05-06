package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class RunnerClass {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/hospital";
        final String username = "root";
        final String password = "mypassword";
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);
            int option = 0;
            while (option != 5) {
                System.out.println("HOSPITAL  MANAGEMENT  SYSTEM");
                System.out.println("==============================");
                System.out.println("1. Add patient");
                System.out.println("2. View patient");
                System.out.println("3. View doctors");
                System.out.println("4. Get appointment");
                System.out.println("5. Exit");
                System.out.println(" ");
                System.out.println("Select the option from below :");
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        patient.viewPatient();
                        break;
                    case 3:
                        doctor.viewDoctor();
                        break;
                    case 4:
                        HospitalManagementSystem.getAppointment(patient, doctor, connection, scanner);
                        break;
                    case 5:
                        System.out.println("Thanks for visiting hospital management system");
                        break;
                    default:
                        System.out.println("you entered invalid option ! please enter valid option.");

                }
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

