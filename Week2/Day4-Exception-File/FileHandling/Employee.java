import java.io.Serializable;
import java.util.Locale;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int empId;
    private final String empName;
    private final String department;
    private final double salary;

    public Employee(int empId, String empName, String department, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "Employee{empId=%d, empName='%s', department='%s', salary=%.2f}",
                empId, empName, department, salary);
    }
}
