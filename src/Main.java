import javax.print.attribute.SupportedValuesAttribute;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {



      /*  FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String theFile = dialog.getFile();
        dialog.dispose();
        System.out.println(theFile + " chosen.");*/

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(                "CSV File", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        String theFile = chooser.getSelectedFile().getAbsolutePath();
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " + theFile);
        }
        else {
            System.out.println("Something went wrong selecting the input file!");
            return;
        }

        Utils utils = new Utils();

       // String filename = "/home/tanya/Desktop/employees.csv";

        List<Employee> employeeList =  utils.readCSVFileToArray(theFile);
        MaxPair max = utils.findHighestCollaborationInDays(employeeList);
        if (max.getEmp1() == 0 && max.getEmp2() == 0 && max.getNumOfDaysTogether()==0) {
            System.out.println("No one collaborated in this company!");
        }
        else {
            System.out.println("Max pair is: emp1: " + max.getEmp1()+" emp2: " + max.getEmp2() + " number of days working together: " + max.getNumOfDaysTogether());
        }
    }
}



