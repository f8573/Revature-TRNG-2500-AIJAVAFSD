import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EmployeeSerializationDemo {
    private static final String DATA_FILE = "employees.dat";

    public static void main(String[] args) {
        Employee[] employees = {
            new Employee(1001, "Priya Sharma", "Information Technology", 85000.00),
            new Employee(1002, "Amit Kumar", "Finance", 78000.00),
            new Employee(1003, "Sara Khan", "Human Resources", 69000.00),
            new Employee(1004, "Dev Patel", "Engineering", 94000.00),
            new Employee(1005, "Nina Roy", "Operations", 72000.00)
        };

        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            output.writeObject(employees);
            System.out.println("Saved 5 employees to " + DATA_FILE + ".");
        } catch (IOException exception) {
            System.err.println("Could not write " + DATA_FILE + ": "
                    + exception.getMessage());
            return;
        }

        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(DATA_FILE))) {
            Employee[] loadedEmployees = (Employee[]) input.readObject();
            System.out.println("Employees read from " + DATA_FILE + ":");
            for (Employee employee : loadedEmployees) {
                System.out.println(employee);
            }
        } catch (IOException | ClassNotFoundException exception) {
            System.err.println("Could not read " + DATA_FILE + ": "
                    + exception.getMessage());
        }
    }
}
