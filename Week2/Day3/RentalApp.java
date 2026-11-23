import java.util.Locale;
import java.util.Scanner;

public class RentalApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int vehicleCount = readNonNegativeInt(scanner, "Enter number of vehicles: ");
            Vehicle[] vehicles = new Vehicle[vehicleCount];

            for (int i = 0; i < vehicleCount; i++) {
                int type = readVehicleType(scanner);

                System.out.print("Enter Vehicle Number: ");
                String vehicleNumber = scanner.nextLine().trim();

                System.out.print("Enter Model: ");
                String model = scanner.nextLine().trim();

                double ratePerDay = readNonNegativeDouble(scanner, "Enter Rate per Day: ");
                int days = readNonNegativeInt(scanner, "Enter Days: ");

                // Inheritance: each subtype reuses Vehicle's shared fields and constructor.
                // Polymorphism: each subtype is stored through this Vehicle reference.
                if (type == 1) {
                    double insurancePerDay = readNonNegativeDouble(
                            scanner, "Enter Insurance per Day: ");
                    vehicles[i] = new Car(vehicleNumber, model, ratePerDay, days,
                            insurancePerDay);
                } else {
                    vehicles[i] = new Bike(vehicleNumber, model, ratePerDay, days);
                }
            }

            System.out.println("--- Rental Details ---");
            double totalRent = 0.0;
            for (Vehicle vehicle : vehicles) {
                // Runtime polymorphism selects the Car or Bike implementations.
                vehicle.displayDetails();
                totalRent += vehicle.calculateRent();
            }

            System.out.printf(Locale.US, "Total Rent Collected: %.1f%n", totalRent);
        }
    }

    private static int readVehicleType(Scanner scanner) {
        while (true) {
            System.out.print("Enter type (1-Car, 2-Bike): ");
            String input = scanner.nextLine().trim();
            if ("1".equals(input)) {
                return 1;
            }
            if ("2".equals(input)) {
                return 2;
            }
            System.out.println("Invalid vehicle type. Enter 1 for Car or 2 for Bike.");
        }
    }

    private static int readNonNegativeInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // The prompt below asks for a valid whole number.
            }
            System.out.println("Please enter a non-negative whole number.");
        }
    }

    private static double readNonNegativeDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (Double.isFinite(value) && value >= 0.0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // The prompt below asks for a valid non-negative amount.
            }
            System.out.println("Please enter a non-negative number.");
        }
    }
}
