import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculations {

    EmployeeDetails employeeDetails = new EmployeeDetails();
    EmployeeAttendance empAttendance = new EmployeeAttendance();

    public double totalGovernmentDeduction(String fileName, String ID) throws IOException
    {
        String sss = employeeDetails.retrieveEmployeeData(employeeDetails.storeEmployeeeData(fileName), ID, 19);
        String pagibig = employeeDetails.retrieveEmployeeData(employeeDetails.storeEmployeeeData(fileName), ID, 20);
        String philhealth = employeeDetails.retrieveEmployeeData(employeeDetails.storeEmployeeeData(fileName), ID, 21);
        return Double.parseDouble(sss) + Double.parseDouble(pagibig)  + Double.parseDouble(philhealth);
        
    }


    public int calculateHoursWork(String employeeNumber, int fromWhatDate, int toWhatDate) throws IOException
    {
        
        String employeeAttendanceArray[][] = empAttendance.storeEmployeeeData("EmployeeAttendance.txt");
        int hour_count = 0;
        //SET THE BREAK TIME / LUNCH TIME TO 1 HOUR
        empAttendance.setBreak(1);
        
        for(int i = 0; i<employeeAttendanceArray.length; i++)
        { 
             if(employeeAttendanceArray[i][0].equals(employeeNumber))
             {
                if(convertDate(employeeAttendanceArray[i][5]) >= fromWhatDate && convertDate(employeeAttendanceArray[i][5]) <= toWhatDate)
                {   
                    if(convertHour(employeeAttendanceArray[i][3]) - 800 == 0)
                    {
                        hour_count = hour_count + (convertHour(employeeAttendanceArray[i][4]) - convertHour(employeeAttendanceArray[i][3]));
                        hour_count = ((hour_count / 100) - empAttendance.getHoursOfBreak()) * 100; 
                    }
                    // IF THE EMPLOYEE IS 10 MINUTES LATE, IT IS OK BECAUSE IT IS A GRACE PERIOD [NO DEDUCTIONS]
                    else if(convertHour(employeeAttendanceArray[i][3]) - 800 <= 10 && convertHour(employeeAttendanceArray[i][3]) - 800 > 0)  
                    {
                        hour_count = hour_count + (convertHour(employeeAttendanceArray[i][4]) - convertHour(employeeAttendanceArray[i][3]) + 100);
                        hour_count = ((hour_count / 100) - empAttendance.getHoursOfBreak()) * 100; 
                    }
                    
                    // IF THE EMPLOYEE IS AN HOUR LATE, IT WILL BE AN HOUR DEDUCTION. IF THEY WILL BE AN HOUR AND A MINUTE AND UP LATE, 2 HOURS WILL BE DEDUCTED
                    else
                    {
                    hour_count = (int)hour_count + convertHour(employeeAttendanceArray[i][4]) - convertHour(employeeAttendanceArray[i][3]);
                    hour_count = ((hour_count / 100) - empAttendance.getHoursOfBreak()) * 100; 
                    }
                }
             }
        }
        return (hour_count/100);
    }

   
    public double getHourlyRate(String empNumber) throws IOException
    {
        String employeeAttendanceArray[][] = employeeDetails.storeEmployeeeData("MotorPH_Employee_Data.txt"); 
        double hourlyRate = 0;
        for(int i = 0; i < employeeAttendanceArray.length; i++)
        {
            for(int j = 0; j < employeeAttendanceArray.length; j++)
            {
                if(employeeAttendanceArray[i][0].equals(empNumber))
                {
                    hourlyRate = Double.parseDouble(employeeAttendanceArray[i][18]);
                }
            }
        }
        return hourlyRate;
    }

    public int convertHour(String Time)
    {
        String time = "";

        for(int i = 0; i < Time.length(); i++)
        {
            if(Time.charAt(i)!= ':')
            {
                time = time + Time.charAt(i);
            }
        }
        return Integer.parseInt(time);
    }

    public int convertDate(String Date)
    {
        String dates = "";

        for(int i = 0; i < Date.length(); i++)
        {
            if(Date.charAt(i)!= '/')
            { 
                dates = dates + Date.charAt(i);
            }
        }
        return Integer.parseInt(dates);
    }

    public double calculateSalaryBasedOnHoursWorked(String employeeID, int startingDate, int endDate) throws IOException
    {
        employeeDetails.setEmployeeFileName("MotorPH_Employee_Data.txt");
        double salary = 0;
        int hoursWork = calculateHoursWork(employeeID, startingDate, endDate);
        salary = hoursWork * getHourlyRate(employeeID);
        return salary + 
        Double.parseDouble(employeeDetails.retrieveEmployeeData(employeeDetails.storeEmployeeeData(employeeDetails.getEmployeeFileName()), employeeID, 14)) + 
        Double.parseDouble(employeeDetails.retrieveEmployeeData(employeeDetails.storeEmployeeeData(employeeDetails.getEmployeeFileName()), employeeID, 15)) + 
        Double.parseDouble(employeeDetails.retrieveEmployeeData(employeeDetails.storeEmployeeeData(employeeDetails.getEmployeeFileName()), employeeID, 16));
    }

    public int displayMonthsOfStay(int startDate, int endDate) throws IOException 
    {
        double monthsOfWork = 0;
        if((((endDate - startDate)/1000) % 99 == 0 || ((endDate - startDate)/1000) >= 0))
        {
            monthsOfWork = 1 + (((endDate/10000) - (startDate/10000)));
        }
        return (int)monthsOfWork/100;
    }
}
