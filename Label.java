import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Label {
    public void setDesign()
    {
        for(int i = 0; i < 100; i++) 
        {
            System.out.print("=");   
        }
        
        System.out.println();
    }

    public void showOptions()
    {
        System.out.println("Choose An Option: ");
        System.out.println("[0] End Program");
        System.out.println("[1] Display Employee"); // EMPLOYEE NO - LAST NAME - FIRST NAME - BIRTHDAY[MM/DD/YYYY] - ADDRESS - PHONE NUMBER - POSITION
        System.out.println("[2] Display Attendance");   // EMPLOYEE NO - LAST NAME - FIRST NAME - TIME IN - TIME OUT - DATE
        System.out.println("[3] Display Payroll");
        System.out.print("Enter here:  ");
    }

    public void showPayrollOptions() throws IOException
    {
        Scanner input = new Scanner(System.in);
        Calculations calculations = new Calculations();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        TaxDeduction taxDeduction = new TaxDeduction();
        InputErrors errors = new InputErrors();
        boolean checkEmpNum = false;
        boolean checkDate = false;
        String empNumber = "";
        String startDate = "";
        String endDate = "";

        while(checkEmpNum == false)
        {
            System.out.print("Enter employee number: ");
            empNumber = input.nextLine();
            if(errors.checkEmployeeNumber(empNumber) == true)
            {
                checkEmpNum = true;
            }
            else{
            System.out.println("Employee Number Not Found!");
            checkEmpNum = false;
            }
        }

        while(checkDate == false)
        {
            System.out.println("To enter date, follow the format MM/dd/yyyy");
            System.out.print("Enter start date: ");
             startDate = input.nextLine();

            if(errors.checkDate(startDate) == true)
            {
                checkDate = true;
                break;
            }
            System.out.println("ERROR! INVALID DATE FORMAT/INPUT");
        }
        
        checkDate = false;

        while(checkDate == false)
        {
            System.out.println("To enter date, follow the format MM/dd/yyyy");
            System.out.print("Enter end date: ");
            endDate = input.nextLine();

            if(errors.checkDate(endDate) == true)
            {
                checkDate = true;
                break;
            }
            System.out.println("ERROR! INVALID DATE FORMAT/INPUT");
        }

        employeeDetails.displayType();
        // SETTING THE FILE NAME USING OUR SETTERS METHOD
        employeeDetails.setEmployeeFileName("MotorPH_Employee_Data.txt");   
        // STORING THE ENTIRE INFORMATION FROM THE EMPLOYEE FILE NAME TO A 2 DIMENSIONAL ARRAY
        String employeeArray[][] = employeeDetails.storeEmployeeeData(employeeDetails.getEmployeeFileName());

        System.out.println("Employee Number: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 0) +
        "\nLast Name: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 1) +
        "\nFirst Name: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 2) +
        "\nBirthday: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 3) +
        "\nAddress: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 4) +
        "\nStatus: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 10) +
        "\nPosition: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 11) +
        "\nBasic Salary: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 13) +
        "\nHourly Rate: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 18) 
        );
        //DISPLAYING EACH MANDATORY GOVERNMENT CONTRIBUTIONS
        setDesign();
        System.out.println("Mandatory Government Contributions");
        setDesign();
        System.out.println("SSS Contribution: " + employeeArray[employeeDetails.retrieveEmployeePosition(empNumber, employeeArray)][19] + 
        "\nPhilHealth Contribution: " + employeeArray[employeeDetails.retrieveEmployeePosition(empNumber, employeeArray)][20] + 
        "\nPag-ibig Contribution: " + employeeArray[employeeDetails.retrieveEmployeePosition(empNumber, employeeArray)][21]);
        //DISPLAYING THE TOTAL MANDATORY GOVERNMENT CONTRIBUTIONS
        System.out.println("Total Mandatory Government Contribution: " + (calculations.totalGovernmentDeduction("MotorPH_Employee_Data.txt", empNumber) *
        calculations.displayMonthsOfStay(calculations.convertDate(startDate),  calculations.convertDate(endDate)))+ "\n");
        //DISPLAYING THE EMPLOYEE'S BENEFITS
        setDesign();
        System.out.println("Employee Benefits");
        setDesign();
        System.out.println("Rice Subsidy: " +  employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 14) +
        "\nPhone Allowance: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 15) +
        "\nClothing Allowance: " + employeeDetails.retrieveEmployeeData(employeeArray, empNumber, 16));

        setDesign();
        //DISPLAYING THE HOURS WORK
        System.out.println("Hours Worked: " + calculations.calculateHoursWork(empNumber, calculations.convertDate(startDate), calculations.convertDate(endDate)));
        //GETTING THE GROSS PAY
        double grossPay = calculations.calculateSalaryBasedOnHoursWorked(empNumber, calculations.convertDate(startDate),  calculations.convertDate(endDate));
        //DISPLAYING THE MONTH/S OF WORK
        double stays = calculations.displayMonthsOfStay(calculations.convertDate(startDate),  calculations.convertDate(endDate));
        System.out.println("Months of Working: " + stays);
        //DISPLAYING THE TAX AMOUNT
        if(((calculations.convertDate(endDate)/10000)- (calculations.convertDate(startDate)/10000)) < 99)
        {
            System.out.println("MONTHLY TAX IS CURRENTLY UNAVAILABLE!");
        }
        else
        {
            double getTaxableIncome = taxDeduction.taxableIncome(empNumber, calculations.convertDate(startDate),  calculations.convertDate(endDate), stays);
            System.out.println("Taxable Income: " + getTaxableIncome);
            double getTax = taxDeduction.getTax(getTaxableIncome, stays);

            System.out.println("Tax Amount: " + taxDeduction.getDeductions(getTax, getTaxableIncome));
        }
        //DISPLAYING THE GROSS PAY
        System.out.println("Gross Pay From [" + startDate + "] to [" + endDate + "]: " + grossPay);
        //DISPLAYING THE NET PAY
        displayNotice(calculations.convertDate(startDate),  calculations.convertDate(endDate));

        if(((calculations.convertDate(endDate)/10000)- (calculations.convertDate(startDate)/10000)) < 99)
        {
            System.out.println("NET PAY IS NOT APPLICABLE FOR CALCULATIONS LESS THAN 1 MONTH! HERE IS THE CURRENT PAY: " + grossPay);
        }
        else
        {
            System.out.println("Net Pay From [" + startDate + "] to [" + endDate + "]: " +
            (taxDeduction.calculateTotalPayWithTaxDeduction(empNumber, calculations.convertDate(startDate),
            calculations.convertDate(endDate), calculations.displayMonthsOfStay(calculations.convertDate(startDate) ,calculations.convertDate(endDate)))));
        }
        

    }

    public void displayNotice(int startDate, int endDate) throws IOException
    {
        if((endDate - startDate) == 0 || ((endDate - startDate)/1000) < 99)
        {
            System.out.println("[ NOTICE! ALL DEDUCTIONS ARE APPLIED EVERY 2ND DAY AFTER JANUARY 2022 ]");
        }
    }


}


