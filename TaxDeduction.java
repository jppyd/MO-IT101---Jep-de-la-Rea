import java.io.IOException;

public class TaxDeduction extends Calculations{
    public double calculateTotalPayWithTaxDeduction(String employeeID, int startDate, int endDate, double stays) throws IOException
    {
       employeeDetails.setEmployeeFileName("MotorPH_Employee_Data.txt");
       //THE SALARY HERE IS ALREADY DEDUCTED BY THE MANDATORY GOVERNMENT DEDUCTIONS AND THE BENEFITS ARE ALREADY ADDED
       double salary = calculateSalaryBasedOnHoursWorked(employeeID, startDate, endDate) -
       (totalGovernmentDeduction(employeeDetails.getEmployeeFileName(), employeeID) * stays);     

       // GETTING THE PERCENTAGE OF THE TAX TO BE APPLIED HERE
       double taxAmount =  getTax(salary, stays);   

       salary = salary - getDeductions(taxAmount, salary);
       return salary;
    }

    public double taxableIncome(String employeeID, int startDate, int endDate, double stays) throws IOException
    {
        employeeDetails.setEmployeeFileName("MotorPH_Employee_Data.txt");
        //THE SALARY HERE IS ALREADY DEDUCTED BY THE MANDATORY GOVERNMENT DEDUCTIONS AND THE BENEFITS ARE ALREADY ADDED
        double taxableIncome = calculateSalaryBasedOnHoursWorked(employeeID, startDate, endDate) -
        (totalGovernmentDeduction(employeeDetails.getEmployeeFileName(), employeeID) * stays);     
        return taxableIncome;
    }

    public double getTax(double monthlySalary, double stays)
    {
        double tax = 0;
        if(monthlySalary/stays > (250000/12) && monthlySalary/stays <= (400000/12))
            tax = 0.15;
        else if(monthlySalary/stays > (400000/12) && monthlySalary/stays <= (800000/12))
            tax = 0.20;
        else if(monthlySalary/stays > (800000/12) && monthlySalary/stays <= (2000000/12))
            tax = 0.25;
        else if(monthlySalary/stays > (2000000/12) && monthlySalary/stays <= (8000000/12))
            tax = 0.30; 
        else if(monthlySalary/stays > (8000000/12))
            tax = 0.35; 
        else if(monthlySalary/stays < (250000/12))
        {
            tax = 0;
        }
            
        return tax;
    }

    public double getDeductions(double tax,  double monthlySalary)
    {
        double dedcutedSalary = monthlySalary;
        if(tax == 0.15)      //CURR SALARY   // THIS WILL GIVE THE EXCESS   * PERCENTAGE
            dedcutedSalary =  (((monthlySalary - (250000/12))) * tax);
        else if(tax == 0.20)
            dedcutedSalary =  (((monthlySalary - (400000/12)) * tax) + (22500/12));
        else if(tax == 0.25)
            dedcutedSalary = (((monthlySalary - (800000/12)) * tax) + (102500/12));
        else if(tax == 0.30)
            dedcutedSalary =  (((monthlySalary - (2000000/12)) * tax) + (402500/12)); 
        else if(tax == 0.35)
            dedcutedSalary =  (((monthlySalary - (8000000/12)) * tax) + (2202500/12));
        else if(tax <= 0)
            dedcutedSalary = 0;
        return dedcutedSalary;
    }

}
