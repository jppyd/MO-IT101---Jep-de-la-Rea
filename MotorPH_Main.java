import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;

public class MotorPH_Main
{
    public static void main(String[] args) throws Exception
    {       
        introduction();
    }

    private static void introduction() throws IOException
    {
        EmployeeFile employeeDetails = new EmployeeDetails();
        EmployeeFile employeeAttendance = new EmployeeAttendance();

        Scanner input = new Scanner(System.in); // INSTANTIATE A SCANNER TO ASK FOR USER'S INPUT FROM THE KEYBOARD
        boolean loopIntroduction = true;
        while(loopIntroduction == true)
        {
            Label label = new Label();   // INSTANTIATE THE DESIGN OBJECT
            label.setDesign();  // CREATE DESIGN
            System.out.println("\t\t\t\t\tMotor PH");   // DISPLAY COMPANY NAME
            label.setDesign();  // CREATE DESIGN
            label.showOptions();    // DISPLAY OPTIONS THAT IS NEEDED FOR THE USER

            char choice = input.next().charAt(0);   // STORE THE USER'S INPUT INSIDE THE VARIABLE NAMED CHOICE
            if(choice == '0')     // TERMINATE THE PROGRAM IF THE USER WILL ENTER 0
            {
                loopIntroduction = false;
            }
            else if(choice == '1')    //THIS OPTION WILL DISPLAY ALL THE EMPLOYEES IN THE FILE
            {
                employeeDetails.displayType();
                employeeDetails.displayEmployee();
            }
            else if(choice == '2')
            {
                employeeAttendance.displayType();
                employeeAttendance.displayEmployee();
            }
            else if(choice == '3')
            {
                label.showPayrollOptions();
            }
            else{
                System.out.println("ERROR INVALID INPUT! PLEASE ENTER ONLY [0][1][2][3]");
            }
            
        }
    }

   
}