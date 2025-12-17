import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WageCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> employeeIDs = new ArrayList<>();
        ArrayList<String> employeeNames = new ArrayList<>();
        ArrayList<Double> employeeHours = new ArrayList<>();
        ArrayList<Double> employeeHourlyRates = new ArrayList<>();
        ArrayList<Double> employeeTaxRates = new ArrayList<>();
        ArrayList<Double> employeeGrossPay = new ArrayList<>();
        ArrayList<Double> employeeTaxPaid = new ArrayList<>();
        ArrayList<Double> employeeNetPay = new ArrayList<>();
        ArrayList<Double> employeeBonuses = new ArrayList<>();

        while (true) {

            String id = getEmployeeID(scanner);
            String name = getEmployeeName(scanner);
            double hours = getHoursWorked(scanner);
            double rate = getHourlyRate(scanner);
            double taxRate = getTaxRate(scanner);

            double grossPay = calculateGrossPay(hours, rate);
            double taxPaid = calculateTax(grossPay, taxRate);
            double bonus = calculateBonus(hours);
            double netPay = grossPay - taxPaid + bonus;

            employeeIDs.add(id);
            employeeNames.add(name);
            employeeHours.add(hours);
            employeeHourlyRates.add(rate);
            employeeTaxRates.add(taxRate);
            employeeGrossPay.add(grossPay);
            employeeTaxPaid.add(taxPaid);
            employeeBonuses.add(bonus);
            employeeNetPay.add(netPay);

            if (!addAnotherEmployee(scanner)) {
                break;
            }
        }

        printEmployeeSummaries(
                employeeIDs,
                employeeNames,
                employeeGrossPay,
                employeeTaxPaid,
                employeeBonuses,
                employeeNetPay
        );

        writeEmployeeSummariesToFile(
                "payroll.txt",
                employeeIDs,
                employeeNames,
                employeeGrossPay,
                employeeTaxPaid,
                employeeBonuses,
                employeeNetPay
        );
    }

    // ---------- INPUT METHODS ----------

    static String getEmployeeID(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        return scanner.nextLine();
    }

    static String getEmployeeName(Scanner scanner) {
        System.out.print("Enter Name: ");
        return scanner.nextLine();
    }

    static double getHoursWorked(Scanner scanner) {
        System.out.print("Enter Hours Worked: ");
        double hours = scanner.nextDouble();
        scanner.nextLine();
        return hours;
    }

    static double getHourlyRate(Scanner scanner) {
        System.out.print("Enter Hourly Rate: ");
        double rate = scanner.nextDouble();
        scanner.nextLine();
        return rate;
    }

    static double getTaxRate(Scanner scanner) {
        System.out.print("Enter Tax Rate (percentage): ");
        double tax = scanner.nextDouble() / 100.0;
        scanner.nextLine();
        return tax;
    }

    static boolean addAnotherEmployee(Scanner scanner) {
        System.out.print("Add another employee? (Yes/No): ");
        return !scanner.nextLine().equalsIgnoreCase("No");
    }

    // ---------- CALCULATION METHODS ----------

    static double calculateGrossPay(double hours, double rate) {
        return hours * rate;
    }

    static double calculateTax(double grossPay, double taxRate) {
        return grossPay * taxRate;
    }

    static double calculateBonus(double hoursWorked) {
        if (hoursWorked >= 50) return 100;
        if (hoursWorked >= 45) return 60;
        if (hoursWorked >= 40) return 50;
        return 0;
    }

    // ---------- OUTPUT METHODS ----------

    static void printEmployeeSummaries(
            ArrayList<String> ids,
            ArrayList<String> names,
            ArrayList<Double> grossPays,
            ArrayList<Double> taxes,
            ArrayList<Double> bonuses,
            ArrayList<Double> netPays
    ) {
        for (int i = 0; i < ids.size(); i++) {
            System.out.println("---- Employee Summary ----");
            System.out.println("ID: " + ids.get(i));
            System.out.println("Name: " + names.get(i));
            System.out.println("Gross Pay: €" + grossPays.get(i));
            System.out.println("Tax Paid: €" + taxes.get(i));
            System.out.println("Bonus: €" + bonuses.get(i));
            System.out.println("Net Pay: €" + netPays.get(i));
            System.out.println("--------------------------");
        }
    }

    static void writeEmployeeSummariesToFile(
            String fileName,
            ArrayList<String> ids,
            ArrayList<String> names,
            ArrayList<Double> grossPays,
            ArrayList<Double> taxes,
            ArrayList<Double> bonuses,
            ArrayList<Double> netPays
    ) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write("Payroll Report");
            writer.newLine();
            writer.write("================");
            writer.newLine();
            writer.newLine();

            for (int i = 0; i < ids.size(); i++) {
                writer.write("---- Employee Summary ----");
                writer.newLine();
                writer.write("ID: " + ids.get(i));
                writer.newLine();
                writer.write("Name: " + names.get(i));
                writer.newLine();
                writer.write("Gross Pay: €" + grossPays.get(i));
                writer.newLine();
                writer.write("Tax Paid: €" + taxes.get(i));
                writer.newLine();
                writer.write("Bonus: €" + bonuses.get(i));
                writer.newLine();
                writer.write("Net Pay: €" + netPays.get(i));
                writer.newLine();
                writer.write("--------------------------");
                writer.newLine();
                writer.newLine();
            }

            System.out.println("Payroll successfully written to " + fileName);

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
