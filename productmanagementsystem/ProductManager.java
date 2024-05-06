package productmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.List;

public class ProductManager {

    public boolean addProduct(Product product) {
        boolean f = false;
        try {
            //JDBC connection
            Connection con = Cp.createC();
            //quiry to insert values in the table
            String sql = "insert into products ( productId,productName,productType,productColor) values (?,?,?,?)";
            //prepared statement
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            //set the values of parameters
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductType());
            preparedStatement.setString(4, product.getProductColor());
            //execute
            preparedStatement.executeUpdate();
            f = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return f;
    }

    public boolean deleteProductById(int productId) {
        boolean f = false;
        try {
            //JDBC connection
            Connection con = Cp.createC();
            //quiry to insert values in the table
            String sql = "delete from products where productId=?";
            //prepared statement
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            //set the values of parameters
            preparedStatement.setInt(1, productId);
            //execute
            preparedStatement.executeUpdate();
            f = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return f;
    }

    public boolean updateProduct(Product product) {
        boolean f = false;
        try {
            //JDBC connection
            Connection con = Cp.createC();
            //quiry to insert values in the table
            String sql = "update products set productName=?,productType=?,productColor=? where productId=?";
            //prepared statement
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            //set the values of parameters

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductType());
            preparedStatement.setString(3, product.getProductColor());
            preparedStatement.setInt(4, product.getProductId());

            //execute
            preparedStatement.executeUpdate();
            f = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return f;
    }

    public void displayProducts() {
        try {
            //JDBC connection
            Connection con = Cp.createC();
            //quiry to insert values in the table
            String sql = "select * from products";
            //prepared statement
          Statement statement = con.createStatement();
            ResultSet set = statement.executeQuery(sql);
            while(set.next()){
                int id=set.getInt(1);
                String name=set.getString(2);
                String type=set.getString(3);
                String color=set.getString(4);

                System.out.println("Product ID   ::  "+ id);
                System.out.println("Product Name ::  "+name);
                System.out.println("Product Type ::  "+type);
                System.out.println("Product color::  "+color);
                System.out.println("---------------------------------------------------------");
            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
