import java.io.*;
public abstract class EmployeeFile {

    private String employeeFileName;
    private int hoursOfBreak = 0;

    //APPLYTING THE ABSTRACTION 
    public abstract void displayEmployee() throws IOException;  

    // CREATING SETTERS AND GETTERS FOR ENCAPSULATION
    //THIS METHOD IS THE SETTERS METHOD WHO WILL BE RESPONSIBLE FOR SETTING THE FILE NAME OF THE EMPLOYEE FILE
    public void setEmployeeFileName(String employeeFileName)    
    {
        this.employeeFileName = employeeFileName;

    }
    //THIS METHOD IS THE GETTERS METHOD WHO WILL BE RESPONSIBLE FOR GETTING THE FILE NAME OF THE EMPLOYEE FILE AND RETURNS ITS NAME
    public String getEmployeeFileName()   
    {
        return employeeFileName;
    }

    public void setBreak(int hoursOfBreak)
    {
        this.hoursOfBreak = hoursOfBreak;
    }

    public int getHoursOfBreak()
    {
        return hoursOfBreak;
    }

    public void displayType()
    {
        System.out.println("************* YOU ARE ENTERING THE EMPLOYEE FILE CLASS ***************************");
    }

    public boolean checkIfEmployeeFileExists(String employeeFileName)
    {
        File employeeData = new File(employeeFileName);

        if(employeeData.exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    

    public String [][] storeEmployeeeData(String fileName) throws IOException
    {
        File employeeFile = new File(fileName);
        String [][] employeeData = null;
        if(checkIfEmployeeFileExists(fileName))
        {
            BufferedReader reader = null;   //USED TO READ INFORMATION FROM THE FILE
            String line = "";
            try 
            {
                reader = new BufferedReader(new FileReader(employeeFile));
                int rows = countRows(employeeFile); // USED TO COUNT THE ROWS IN ORDER TO ALLOCATE ENOUGH SPACE FOR THE ARRAY

                if(rows > 0)
                {
                    line = reader.readLine();
                    int columns = countCommas(line);    // USED TO COUNT THE ROWS IN ORDER TO ALLOCATE ENOUGH SPACE FOR THE ARRAY
                    employeeData = new String[rows][columns];
                    int rowIndex = 0;

                    while((line = reader.readLine()) != null)
                    {
                        String [] fileColumn = line.split("\\|");
                        for(int columnIndex = 0; columnIndex < columns; columnIndex++)
                        {
                            if(columnIndex < fileColumn.length)
                            {
                                employeeData[rowIndex][columnIndex] = fileColumn[columnIndex];
                            }
                            else
                            {
                                employeeData[rowIndex][columnIndex] = "";
                            }
                        }
                        rowIndex++;
                    }
                }
            }
             catch (Exception e) 
            {
                // TODO: handle exception
            }
            finally{
                if(reader != null)
                reader.close();
            }
        }
        else
        {
            System.out.println("File is either corrupt or it doesn't exist");
        }
        return employeeData;
    }

    

    public void setLabel(String fileName, boolean shouldSkip, int whereToSkip, int cutArray) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String labels = reader.readLine();
        String labelArr[] = labels.split("\\|");

        for(int i = 0; i <labelArr.length - cutArray; i++)
        {
            if(shouldSkip)
            {
                if(i == whereToSkip)
                continue;
            }
            System.out.printf("%-20s", labelArr[i]);
        }
        System.out.println();
        reader.close();
    }

    

    public int countCommas(String line)
    {
        int count = 0;
        for(int i = 0; i < line.length();i++)
        {
            if(line.charAt(i) == '|')
            {
                count++;
            }
        }
        return count + 1;
    }

    public int countRows(File emp_file)
    {
        int rows = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(emp_file));
        
            while(reader.readLine() != null)
            {
                rows++;
            }
            reader.close();
        }
        catch(Exception err){
            System.out.println("File not found");
        }
        return rows - 1;
    }

    public void displayEmployeeArray(String [][] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
            {
                System.out.printf("%-20s", arr[i][j]);
            }
            System.out.println();
        }
    }

    public String retrieveEmployeeData(String arr[][], String id, int index)
    {
        String dataToRetrieve = "";
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i][0].equals(id))
            {
                dataToRetrieve = arr[i][index];
            }
        }
        return dataToRetrieve;
    }

    public int retrieveEmployeePosition(String id, String [][] array)
    {
        int position = 0;
        for(int i = 0; i < array.length; i++)
        {
            if(array[i][0].equals(id))
            {
                break;
            }
            else{
                position++;
            }
        }
        return position;
    }
}


