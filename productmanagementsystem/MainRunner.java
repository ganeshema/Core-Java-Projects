package productmanagementsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainRunner {
    public static void main(String[] args) throws IOException {
        ProductManager productManager = new ProductManager();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int choice=0;
        while (choice!=5) {
            System.out.println("===Welcome to the Product Management System===");
            System.out.println("-----------------------------------------------");
            System.out.println("1. Add product");
            System.out.println("2. Delete product");
            System.out.println("3. Update product");
            System.out.println("4. Display products");
            System.out.println("5. Exit");
            System.out.println(" ");
            System.out.println("Please select a option from above list:");
            try {
                choice = Integer.parseInt(bf.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (choice == 1) {
                System.out.println("Enter the productId");
                int id = 0;
                id = Integer.parseInt(bf.readLine());
                System.out.println("Enter the product Name");
                String name = bf.readLine();
                System.out.println("Enter the Type of the product");
                String type = bf.readLine();
                System.out.println("Enter the color of the product");
                String color = bf.readLine();
                Product product = new Product(id, name, type, color);
                boolean ans = productManager.addProduct(product);
                if (ans) {
                    System.out.println("product has added successfully");
                }
            } else if (choice == 2) {
                System.out.println("Enter the product ID to be deleted");
                int productId = Integer.parseInt(bf.readLine());
                boolean confirm = productManager.deleteProductById(productId);
                if (confirm) {
                    System.out.println("You have deleted the product Id no: " + productId);
                }
            } else if (choice == 3) {
                System.out.println("Enter the ID of the product to be updated ");
                int id = 0;
                id = Integer.parseInt(bf.readLine());
                System.out.println("Enter the new product Name");
                String name = bf.readLine();
                System.out.println("Enter the new Type of the product");
                String type = bf.readLine();
                System.out.println("Enter the new color of the product");
                String color = bf.readLine();
                Product product = new Product(id, name, type, color);
                boolean response = productManager.updateProduct(product);
                if (response) {
                    System.out.println("you have updated Id no  " + id);
                }

            } else if (choice == 4) {
                productManager.displayProducts();
            } else if (choice == 5) {
                System.out.println("Thank you for using this application, Good Bye !!");
            } else {
                System.out.println("Error -- Please Enter the valid option !");
                System.out.println(" ");
            }
        }
    }
}








