import java.util.Locale;

/** Base class for vehicles that can be rented. */
public class Vehicle {
    protected final String vehicleNumber;
    protected final String model;
    protected final double ratePerDay;
    protected final int days;

    public Vehicle(String vehicleNumber, String model, double ratePerDay, int days) {
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.ratePerDay = ratePerDay;
        this.days = days;
    }

    public double calculateRent() {
        return ratePerDay * days;
    }

    public void displayDetails() {
        System.out.printf(Locale.US,
                "Vehicle | %-12s | %-6s | Days: %d | Rent: %.1f%n",
                vehicleNumber, model, days, calculateRent());
    }
}
