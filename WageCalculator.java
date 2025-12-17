import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class WageCalculator{

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        String employeeID = "";
        String employeeName = "";
        double hoursWorked = 0;
        double taxRate = 0;
        String confirmation = "Yes";
        double bonus = 0;
        double grossPay = 0;
        double netPay = 0;
        double taxPaid = 0;
        double hourlyRate = 0;

        ArrayList<String> employeeIDs = new ArrayList<>();
        ArrayList<String> employeeNames = new ArrayList<>();
        ArrayList<Double> employeeHours = new ArrayList<>();
        ArrayList<Double> employeeTaxRates = new ArrayList<>();
        ArrayList<Double> employeeGrossPay = new ArrayList<>();
        ArrayList<Double> employeeTaxPaid = new ArrayList<>();
        ArrayList<Double> employeeNetPay = new ArrayList<>();
        ArrayList<Double> employeeBonuses = new ArrayList<>();
        ArrayList<Double> employeeHourlyRates = new ArrayList<>();

        while (true){
        
        bonus = 0;
        //Get Employee ID
        System.out.println("Enter Employee ID: ");
        employeeID = scanner.nextLine();
        employeeIDs.add(employeeID);

        //Get Employee Name
        System.out.println("Enter Name: ");
        employeeName = scanner.nextLine();
        employeeNames.add(employeeName);

        //Get Hours Worked
        System.out.println("Enter Hours Worked ");
        hoursWorked = scanner.nextDouble();
        scanner.nextLine();
        employeeHours.add(hoursWorked);

        //Get Hourly Rates
        System.out.println("Enter Hourly Rate ");
        hourlyRate = scanner.nextDouble();
        scanner.nextLine();
        employeeHourlyRates.add(hourlyRate);


        //Get tax rate
        System.out.println("Enter Tax Rate (as percentage, e.g., 50 for 50%");
        taxRate = scanner.nextDouble()/100.0;
        scanner.nextLine();
        employeeTaxRates.add(taxRate);

        //Calculations
        grossPay = hoursWorked * hourlyRate;
        employeeGrossPay.add(grossPay);

        taxPaid = grossPay * taxRate;
        employeeTaxPaid.add(taxPaid);

        netPay = grossPay - taxPaid;
        

        // Determine bonus
        if (hoursWorked >= 50)
        bonus = 100;
        else if (hoursWorked >= 45)
            bonus = 60;
        else if (hoursWorked >= 40)
            bonus = 50;
        else
            bonus = 0;

        netPay += bonus;
        employeeBonuses.add(bonus);
        employeeNetPay.add(netPay);
        


        //Confirmation
        System.out.println("Add another employee? (Yes/No)");
        confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("No")){
            break;
        }
    

        }

        for (int i = 0; i < employeeIDs.size(); i++){
        System.out.println("---- Employee Summary ----");
        System.out.println("ID: " + employeeIDs.get(i));
        System.out.println("Name: " + employeeNames.get(i));
        System.out.println("Gross Pay: €" + employeeGrossPay.get(i));
        System.out.println("Tax Paid: €" + employeeTaxPaid.get(i));
        System.out.println("Net Pay: €" + employeeNetPay.get(i));
        System.out.println("Bonus: €" + employeeBonuses.get(i));
        System.out.println("--------------------------");
    }
    }
}