package com.citypower.billing;

import java.util.Locale;
import java.util.Scanner;

/** CityPower console electricity bill using progressive unit slabs. */
public class ElectricityBillApplication {
    private static final int FIRST_SLAB_LIMIT = 100;
    private static final int SECOND_SLAB_LIMIT = 300;
    private static final double FIRST_SLAB_RATE = 3.00;
    private static final double SECOND_SLAB_RATE = 5.00;
    private static final double THIRD_SLAB_RATE = 7.50;
    private static final double FIXED_CHARGE = 50.00;
    private static final double SURCHARGE_THRESHOLD = 2000.00;
    private static final double SURCHARGE_RATE = 0.05;
    private static final double ZERO_AMOUNT = 0.00;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Consumer number: ");
        String consumerNumber = scanner.nextLine().trim();
        System.out.print("Consumer name: ");
        String consumerName = scanner.nextLine().trim();
        int previousReading = readInteger(scanner, "Previous meter reading: ");
        int currentReading = readInteger(scanner, "Current meter reading: ");

        if (consumerNumber.isEmpty() || consumerName.isEmpty()) {
            System.out.println("Consumer number and name are required.");
        } else if (!isValidReadingPair(previousReading, currentReading)) {
            System.out.println("Invalid readings: current reading cannot be less than previous");
        } else {
            printBill(consumerNumber, consumerName, previousReading, currentReading);
        }
        scanner.close();
    }

    /** Progressive slabs: first 100 at Rs. 3, next 200 at Rs. 5, remainder at Rs. 7.50. */
    public static double calculateEnergyCharge(int units) {
        if (units < 0) {
            throw new IllegalArgumentException("Units cannot be negative.");
        }
        if (units <= FIRST_SLAB_LIMIT) {
            return units * FIRST_SLAB_RATE;
        }
        if (units <= SECOND_SLAB_LIMIT) {
            return FIRST_SLAB_LIMIT * FIRST_SLAB_RATE
                    + (units - FIRST_SLAB_LIMIT) * SECOND_SLAB_RATE;
        }
        int secondSlabUnits = SECOND_SLAB_LIMIT - FIRST_SLAB_LIMIT;
        return FIRST_SLAB_LIMIT * FIRST_SLAB_RATE
                + secondSlabUnits * SECOND_SLAB_RATE
                + (units - SECOND_SLAB_LIMIT) * THIRD_SLAB_RATE;
    }

    /** The 5% surcharge applies only when energy charge is strictly above Rs. 2,000. */
    public static double calculateSurcharge(double energyCharge) {
        if (energyCharge < ZERO_AMOUNT || Double.isNaN(energyCharge) || Double.isInfinite(energyCharge)) {
            throw new IllegalArgumentException("Energy charge must be a finite, non-negative amount.");
        }
        return energyCharge > SURCHARGE_THRESHOLD
                ? energyCharge * SURCHARGE_RATE
                : ZERO_AMOUNT;
    }

    public static double calculateTotal(double energyCharge) {
        return energyCharge + FIXED_CHARGE + calculateSurcharge(energyCharge);
    }

    public static int calculateUnits(int previousReading, int currentReading) {
        if (!isValidReadingPair(previousReading, currentReading)) {
            throw new IllegalArgumentException(
                    "Current reading cannot be less than previous reading.");
        }
        return currentReading - previousReading;
    }

    public static boolean isValidReadingPair(int previousReading, int currentReading) {
        return previousReading >= 0 && currentReading >= previousReading;
    }

    public static void printBill(String consumerNumber, String consumerName,
            int previousReading, int currentReading) {
        if (!isValidReadingPair(previousReading, currentReading)) {
            System.out.println("Invalid readings: current reading cannot be less than previous");
            return;
        }
        int units = calculateUnits(previousReading, currentReading);
        double energyCharge = calculateEnergyCharge(units);
        double surcharge = calculateSurcharge(energyCharge);
        double total = calculateTotal(energyCharge);

        System.out.println("========== CITYPOWER ELECTRICITY BILL ==========");
        System.out.printf("Consumer number : %s%n", consumerNumber);
        System.out.printf("Consumer name   : %s%n", consumerName);
        System.out.printf("Previous reading: %d%n", previousReading);
        System.out.printf("Current reading : %d%n", currentReading);
        System.out.printf("Units consumed  : %d%n", units);
        System.out.printf("Energy charge   : Rs. %,.2f%n", energyCharge);
        System.out.printf("Fixed charge    : Rs. %,.2f%n", FIXED_CHARGE);
        System.out.printf("Surcharge       : Rs. %,.2f%n", surcharge);
        System.out.printf("Total bill      : Rs. %,.2f%n", total);
        System.out.println("================================================");
    }

    private static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException ex) {
                // Print one clear message and prompt again for malformed input.
            }
            System.out.println("Enter a non-negative whole number.");
        }
    }
}
