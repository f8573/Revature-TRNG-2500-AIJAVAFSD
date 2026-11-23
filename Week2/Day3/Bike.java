import java.util.Locale;

/** A bike receives a 10 percent discount on its base rental cost. */
public class Bike extends Vehicle {
    public Bike(String vehicleNumber, String model, double ratePerDay, int days) {
        super(vehicleNumber, model, ratePerDay, days);
    }

    @Override
    public double calculateRent() {
        return super.calculateRent() * 0.90;
    }

    @Override
    public void displayDetails() {
        System.out.printf(Locale.US,
                "Bike | %-12s | %-6s | Days: %d | Rent: %.1f%n",
                vehicleNumber, model, days, calculateRent());
    }
}
