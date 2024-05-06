package bankingsystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        DataBaseConnetion dbc=new DataBaseConnetion();
        User user=new User(dbc.jdbcConnection(), scanner);
        Account account=new Account(dbc.jdbcConnection(), scanner);
        AccountManager accountManager=new AccountManager(dbc.jdbcConnection(), scanner);
        String email;
        long account_number;
        int choice=0;
        while(choice!=3){
            System.out.println("---------------------------------------");
            System.out.println("*****WELCOME TO BANKING SYSTEM*******");
            System.out.println("---------------------------------------");
            System.out.println("1. Register.");
            System.out.println("2. Login.");
            System.out.println("3. Exit.");
            System.out.println("-----------------------------");
            System.out.println("Please select the option.");
            try {
                choice = scanner.nextInt();
            switch (choice){
                case 1:
                    user.register();
                    break;
                case 2:
                    email= user.login();
                    if(email!=null){
                        System.out.println(" ");
                        System.out.println("User Logged in !");
                        if(!account.account_exist(email)){
                            System.out.println();
                            System.out.println("1.Open new Bank Account.");
                            System.out.println("2.Exit");
                            if(scanner.nextInt()==1) {
                                account_number = account.openAccount(email);
                                System.out.println("Your account has created successfully !!");
                                System.out.println("Your New Account Number is : " + account_number);
                            }else{
                                break;
                            }
                        }
                        account_number=account.getAccountNumber(email);
                        int choice2=0;
                        while(choice2!=5){
                            System.out.println();
                            System.out.println("1. Debit Money.");
                            System.out.println("2. Credit Money");
                            System.out.println("3. Transfer Money.");
                            System.out.println("4. Check Balance.");
                            System.out.println("5. Exit.");
                            System.out.println("------------------------------");
                            System.out.println("Enter the option.");
                            choice2=scanner.nextInt();
                            switch (choice2){
                                case 1:
                                    accountManager.debit_money(account_number);
                                    break;
                                case 2:
                                    accountManager.credit_money(account_number);
                                    break;
                                case 3:
                                    accountManager.transfer_money(account_number);
                                    break;
                                case 4:
                                    accountManager.getBalance(account_number);
                                    break;
                                case 5:
                                    System.out.println("Logging Out.");
                                    break;
                                default:
                                    System.out.println("Please select correct option.");

                            }

                    }
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using banking system.");
                    System.out.println("Exiting system !");
                    break;
                default:
                    System.out.println("Please enter the valid option.");
            }
            }catch (InputMismatchException e){
                System.out.println("Please Enter only integer value.");
                break;
            }

        }



    }
}
