package com.smartstore.billing;

import java.util.Locale;
import java.util.Scanner;

/** Refactored SmartStore console billing program. */
public class BillingApplication {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        String customerName = readRequiredText(scanner, "Customer name: ");
        String productName = readRequiredText(scanner, "Product name: ");
        double price = readPositiveDouble(scanner, "Product price: ");
        int quantity = readPositiveInteger(scanner, "Quantity: ");

        double totalAmount = price * quantity;
        double discountAmount = calculateDiscount(totalAmount);
        double discountPercentage = calculateDiscountPercentage(totalAmount);
        double finalAmount = totalAmount - discountAmount;
        printBill(customerName, productName, price, quantity, totalAmount,
                discountPercentage, discountAmount, finalAmount);
        scanner.close();
    }

    /** Returns the discount amount in rupees. */
    public static double calculateDiscount(double totalAmount) {
        if (totalAmount < 0 || Double.isNaN(totalAmount) || Double.isInfinite(totalAmount)) {
            throw new IllegalArgumentException("Total amount must be non-negative and finite.");
        }
        double discountAmount = totalAmount * calculateDiscountPercentage(totalAmount);
        return Math.round(discountAmount * 100.0) / 100.0;
    }

    public static double calculateDiscountPercentage(double totalAmount) {
        if (totalAmount >= 5000.00) {
            return 0.10;
        }
        if (totalAmount >= 1000.00 && totalAmount < 5000.00) {
            // This implements the assignment's suggested boundary: below Rs. 5,000.
            return 0.05;
        }
        return 0.00;
    }

    public static double calculateFinalAmount(double totalAmount) {
        return totalAmount - calculateDiscount(totalAmount);
    }

    private static void printBill(String customerName, String productName, double price,
            int quantity, double totalAmount, double discountPercentage,
            double discountAmount, double finalAmount) {
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
    }

    private static String readRequiredText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Please enter a value.");
        }
    }

    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value > 0 && !Double.isNaN(value) && !Double.isInfinite(value)) {
                    return value;
                }
            } catch (NumberFormatException ex) {
                // A bad entry is handled with a message and another prompt.
            }
            System.out.println("Enter a finite number greater than zero.");
        }
    }

    private static int readPositiveInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ex) {
                // A bad entry is handled with a message and another prompt.
            }
            System.out.println("Enter a whole number greater than zero.");
        }
    }
}
