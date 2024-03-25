import java.io.IOException;

public class InputErrors {
    EmployeeDetails employeeDetails = new EmployeeDetails();
    public boolean checkEmployeeNumber(String employeeNumber) throws IOException
    {
        boolean result = false;
        employeeDetails.setEmployeeFileName("MotorPH_Employee_Data.txt"); 
        if(employeeDetails.retrieveEmployeeData(employeeDetails.storeEmployeeeData(employeeDetails.getEmployeeFileName()),employeeNumber, 0) == "")
        {
            result = false;
        }
        else
        {
            result = true;
        }

        return result;
    }

    public boolean checkDate(String date) throws IOException
    {
        boolean result = false;
        int i = 0;
        while(i < date.length())
        {
            if(date.charAt(i) < 47 || date.charAt(i) > 57 || date.length() != 10)
            {
                result = false;
                break;
            }
            else
            {
                result = true;
            }
            i++;
        }

        return result;
    }
}
