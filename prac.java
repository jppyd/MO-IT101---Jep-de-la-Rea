import java.io.IOException;

public class prac extends EmployeeFile{
    public static void main(String[] args) throws Exception 
    {
        new prac().displayEmployee();
    }

    @Override
    public void displayEmployee() throws IOException {
        EmployeeFile f = new EmployeeDetails();
        f.setEmployeeFileName("MotorPH_Employee_Data.txt");
        String[][] arr = storeEmployeeeData(f.getEmployeeFileName());

        for(int i = 0; i < arr.length; i++)
        {
            if(i != 4 && !(i >= 6 && i <= 9) && i <= 13)
            {
                for (int j = 0; j < arr[0].length; j++)
                {
                
                System.out.print(arr[i][j] + "|");
                }
            }

            else
            {
                
            }
            
            System.out.println();
        }
    }
   
}
