import java.io.IOException;

public class EmployeeDetails extends EmployeeFile{

    @Override
    public void displayEmployee() throws IOException
    {
        setEmployeeFileName("MotorPH_Employee_Data.txt");   // SETTING THE FILE NAME USING OUR SETTERS METHOD

        String basicEmployeeDataArray[][] = storeEmployeeeData(getEmployeeFileName());
        setLabel(getEmployeeFileName(), true, 4, 13);
        
        for(int i = 0; i < basicEmployeeDataArray.length; i++)
        {
            for(int j = 0; j < basicEmployeeDataArray[i].length - 13; j++)
            {
                if(j == 4)
                continue;
                System.out.printf("%-20s", basicEmployeeDataArray[i][j]);
            }
            System.out.println();
        }
    }

    // THIS IS POLYMORPHISM SINCE I AM OVERRIDING A METHOD FROM THE SUPERCLASS WITH THE SAME METHOD NAME
    @Override
    public void displayType()
    {
        System.out.println("********************************************************************** BASIC EMPLOYEE DETAILS **********************************************************************\n");
    }
}
