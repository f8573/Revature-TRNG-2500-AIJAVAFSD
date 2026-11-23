import java.util.Locale;

/** A car adds a daily insurance charge to its base rental cost. */
public class Car extends Vehicle {
    private final double insurancePerDay;

    public Car(String vehicleNumber, String model, double ratePerDay, int days,
            double insurancePerDay) {
        super(vehicleNumber, model, ratePerDay, days);
        this.insurancePerDay = insurancePerDay;
    }

    @Override
    public double calculateRent() {
        return super.calculateRent() + insurancePerDay * days;
    }

    @Override
    public void displayDetails() {
        System.out.printf(Locale.US,
                "Car  | %-12s | %-6s | Days: %d | Rent: %.1f%n",
                vehicleNumber, model, days, calculateRent());
    }
}
