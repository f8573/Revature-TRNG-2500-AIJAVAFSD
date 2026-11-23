package com.smartstore.billing;

import java.util.Locale;
import java.util.Scanner;

/** Independent final challenge: calculate an employee's gross and net salary. */
public class SalaryCalculator {
    private static final double HRA_RATE = 0.20;
    private static final double DA_RATE = 0.10;
    private static final double PROFESSIONAL_TAX = 200.00;
    private static final double TAX_THRESHOLD = 20000.00;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Employee ID: ");
        String employeeId = scanner.nextLine().trim();
        System.out.print("Employee name: ");
        String employeeName = scanner.nextLine().trim();
        System.out.print("Basic salary: ");
        double basicSalary;
        try {
            basicSalary = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid employee details or salary.");
            scanner.close();
            return;
        }

        if (employeeId.isEmpty() || employeeName.isEmpty()
                || basicSalary < 0 || Double.isNaN(basicSalary) || Double.isInfinite(basicSalary)) {
            System.out.println("Invalid employee details or salary.");
            scanner.close();
            return;
        }

        double hra = calculateHra(basicSalary);
        double da = calculateDa(basicSalary);
        double grossSalary = calculateGross(basicSalary);
        double professionalTax = calculateProfessionalTax(grossSalary);
        double netSalary = grossSalary - professionalTax;
        printSalary(employeeId, employeeName, basicSalary, hra, da,
                grossSalary, professionalTax, netSalary);
        scanner.close();
    }

    public static double calculateHra(double basicSalary) {
        return roundToCents(basicSalary * HRA_RATE);
    }

    public static double calculateDa(double basicSalary) {
        return roundToCents(basicSalary * DA_RATE);
    }

    public static double calculateGross(double basicSalary) {
        return roundToCents(basicSalary + calculateHra(basicSalary) + calculateDa(basicSalary));
    }

    public static double calculateProfessionalTax(double grossSalary) {
        // Compare the displayed currency amount so floating-point noise at Rs. 20,000
        // does not accidentally trigger tax.
        return roundToCents(grossSalary) > TAX_THRESHOLD ? PROFESSIONAL_TAX : 0.00;
    }

    private static double roundToCents(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

    private static void printSalary(String employeeId, String employeeName, double basicSalary,
            double hra, double da, double grossSalary, double tax, double netSalary) {
        System.out.println("========== EMPLOYEE SALARY ==========");
        System.out.println("Employee ID      : " + employeeId);
        System.out.println("Employee name    : " + employeeName);
        System.out.printf("Basic salary     : Rs. %.2f%n", basicSalary);
        System.out.printf("HRA (20%%)        : Rs. %.2f%n", hra);
        System.out.printf("DA (10%%)         : Rs. %.2f%n", da);
        System.out.printf("Gross salary     : Rs. %.2f%n", grossSalary);
        System.out.printf("Professional tax : Rs. %.2f%n", tax);
        System.out.printf("Net salary       : Rs. %.2f%n", netSalary);
        System.out.println("=====================================");
    }
}
