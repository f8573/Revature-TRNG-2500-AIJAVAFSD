package com.citypower.billing;

import java.util.Locale;
import java.util.Scanner;

/** Initial single-main version retained to compare with the refactored application. */
public class ElectricityBillApplicationOriginal {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Consumer number: ");
        String consumerNumber = scanner.nextLine().trim();
        System.out.print("Consumer name: ");
        String consumerName = scanner.nextLine().trim();
        System.out.print("Previous meter reading: ");
        int previousReading = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Current meter reading: ");
        int currentReading = Integer.parseInt(scanner.nextLine().trim());

        if (currentReading < previousReading || previousReading < 0) {
            System.out.println("Invalid readings: current reading cannot be less than previous");
            scanner.close();
            return;
        }

        int units = currentReading - previousReading;
        double energyCharge;
        if (units <= 100) {
            energyCharge = units * 3.00;
        } else if (units <= 300) {
            energyCharge = 100 * 3.00 + (units - 100) * 5.00;
        } else {
            energyCharge = 100 * 3.00 + 200 * 5.00 + (units - 300) * 7.50;
        }
        double surcharge = energyCharge > 2000.00 ? energyCharge * 0.05 : 0.00;
        double total = energyCharge + 50.00 + surcharge;

        System.out.println("========== CITYPOWER ELECTRICITY BILL ==========");
        System.out.printf("Consumer number : %s%n", consumerNumber);
        System.out.printf("Consumer name   : %s%n", consumerName);
        System.out.printf("Previous reading: %d%n", previousReading);
        System.out.printf("Current reading : %d%n", currentReading);
        System.out.printf("Units consumed  : %d%n", units);
        System.out.printf("Energy charge   : Rs. %,.2f%n", energyCharge);
        System.out.printf("Fixed charge    : Rs. %,.2f%n", 50.00);
        System.out.printf("Surcharge       : Rs. %,.2f%n", surcharge);
        System.out.printf("Total bill      : Rs. %,.2f%n", total);
        System.out.println("================================================");
        scanner.close();
    }
}
