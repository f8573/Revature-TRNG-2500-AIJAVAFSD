package com.smartstore.billing;

import java.util.Locale;
import java.util.Scanner;

/** Initial single-method version used for the refactoring comparison. */
public class BillingApplicationOriginal {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Product name: ");
        String productName = scanner.nextLine();
        System.out.print("Product price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        double totalAmount = price * quantity;
        double discountPercentage;
        if (totalAmount >= 5000.00) {
            discountPercentage = 0.10;
        } else if (totalAmount >= 1000.00 && totalAmount < 5000.00) {
            // Treat the written Rs. 4,999 upper boundary as all amounts below Rs. 5,000.
            discountPercentage = 0.05;
        } else {
            discountPercentage = 0.00;
        }
        double discountAmount = totalAmount * discountPercentage;
        double finalAmount = totalAmount - discountAmount;

        System.out.println("========== CUSTOMER BILL - SMARTSTORE ==========");
        System.out.printf("Customer         : %s%n", customerName);
        System.out.printf("Product          : %s%n", productName);
        System.out.printf("Price            : Rs. %.2f%n", price);
        System.out.printf("Quantity         : %d%n", quantity);
        System.out.printf("Total            : Rs. %.2f%n", totalAmount);
        System.out.printf("Discount         : %.0f%%%n", discountPercentage * 100);
        System.out.printf("Discount amount  : Rs. %.2f%n", discountAmount);
        System.out.println("-----------------------------------------------");
        System.out.printf("Final amount     : Rs. %.2f%n", finalAmount);
        System.out.println("===============================================");
        scanner.close();
    }
}
