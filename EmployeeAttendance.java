import java.io.IOException;
                                //INHERITANCE
public class EmployeeAttendance extends EmployeeFile
{
    @Override
    public void displayEmployee() throws IOException
    {
        // ENCAPSULATION - GETTERS AND SETTERS
        setEmployeeFileName("EmployeeAttendance.txt");
        String employeeAttendanceArray[][] = storeEmployeeeData(getEmployeeFileName());
        setLabel(getEmployeeFileName(), false, 0, 0);
        displayEmployeeArray(employeeAttendanceArray);
    }

    // THIS IS POLYMORPHISM SINCE I AM OVERRIDING A METHOD FROM THE SUPERCLASS WITH THE SAME METHOD NAME
    @Override
    public void displayType()
    {
        System.out.println("************************************************ EMPLOYEE ATTENDANCE ************************************************\n");
    }
}