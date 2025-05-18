import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utils {

    private List<List<String>> populateData = new ArrayList<>();

    public List<Employee> readCSVFileToArray(String filename){
        List<Employee> listOfEmployees = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date tempDate = calendar.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = format1.format(tempDate);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 2; i < 4; i++) {
                    //System.out.println("Testing for NULL... " + values[i]);
                    if (values[i].trim().equalsIgnoreCase("null")) {
                        values[i] = currentDate.toString();
                        System.out.println("Replaced NULL with date: " + values[i]);
                    }
                }
                Integer empID = Integer.parseInt(values[0].trim());
                Integer projID = Integer.parseInt(values[1].trim());

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = formatter.parse(values[2].trim());
                Date endDate = formatter.parse(values[3].trim());
                Employee employee = new Employee(empID, projID, startDate, endDate);
                listOfEmployees.add(employee);
            }

        } catch (Exception e) {
            //  Block of code to handle errors
            System.out.printf("Exception caught " + e.getMessage());
        }
        return listOfEmployees;
    }

    public MaxPair findHighestCollaborationInDays (List<Employee> listOfEmp) {

        //holds max pair employees and their overlap result
        MaxPair maxPair = new MaxPair(0,0,0, 0);
        Integer indexForPopulateDataArray=0;


        for (int emp1Index = 0; emp1Index < listOfEmp.size() - 1; emp1Index++) {

            for (int emp2Index = emp1Index + 1; emp2Index < listOfEmp.size(); emp2Index++) {
                Integer FirstEmpProject = listOfEmp.get(emp1Index).getProject();
                Integer SecondEmpProject = listOfEmp.get(emp2Index).getProject();

                if (FirstEmpProject == SecondEmpProject) {

                    Date startDate1 = listOfEmp.get(emp1Index).getStartDate();
                    Date startDate2 = listOfEmp.get(emp2Index).getStartDate();
                    Date endDate1 = listOfEmp.get(emp1Index).getEndDate();
                    Date endDate2 = listOfEmp.get(emp2Index).getEndDate();

                    long daysOverlap = 0;
                    try {
                        daysOverlap = getOverlapInDays(startDate1, startDate2, endDate1, endDate2);
                    } catch (ParseException e) {
                        System.out.println("ParseException in Utils class findHighestCollaborationInDays function");
                       // throw new RuntimeException(e);
                    }

                    List<String> currentPairToAdd = new ArrayList<>();
                    currentPairToAdd.add(listOfEmp.get(emp1Index).getEmpID().toString());
                    currentPairToAdd.add(listOfEmp.get(emp2Index).getEmpID().toString());
                    currentPairToAdd.add(FirstEmpProject.toString());
                    currentPairToAdd.add(String.valueOf(daysOverlap));

                    populateData.add(currentPairToAdd);

                    indexForPopulateDataArray++;

                    System.out.println("Days overlap: " + daysOverlap + ", for employees: " + listOfEmp.get(emp1Index).getEmpID().toString() + " and " + listOfEmp.get(emp2Index).getEmpID().toString());
                    if (daysOverlap > maxPair.getNumOfDaysTogether()){
                        maxPair.setEmp1(listOfEmp.get(emp1Index).getEmpID());
                        maxPair.setEmp2(listOfEmp.get(emp2Index).getEmpID());
                        maxPair.setProject(listOfEmp.get(emp1Index).getProject());
                        maxPair.setNumOfDaysTogether(daysOverlap);
                    }
                }
            }

        }

        int size = populateData.size();
        String [][] popData = new String[size][4];

        for (int i = 0; i<size; i++){
            for (int j = 0; j<4; j++){
                popData[i][j]=populateData.get(i).get(j);
            }
        }

        PairsTable showTable = new PairsTable(popData);

        return maxPair;
    }

    public long dateDifferenceInDays(Date EndDate, Date StartDate) throws java.text.ParseException {

        long diffInMillies = Math.abs(EndDate.getTime() - StartDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

    //this function subtracts the earlier end date from the later start date thus getting the overlap of the two periods
    public long getOverlapInDays(Date startDate1, Date startDate2, Date endDate1, Date endDate2) throws ParseException {

         Date earlierEndDate;
        Date laterStartDate;

        if (startDate1.compareTo(startDate2) == 1) {
            //System.out.println("StartDateOne is after StartDateTwo!");
            laterStartDate = startDate1;
        } else {
            //System.out.println("StartDateOne is before StartDateTwo!");
            laterStartDate = startDate2;
        }
        if (endDate1.compareTo(endDate2) == 1) {
            //System.out.println("EndDateOne is after EndDateTwo!");
            earlierEndDate = endDate2;
        } else {
            //System.out.println("EndDateOne is before EndDateTwo!");
            earlierEndDate = endDate1;
        }
        long overlapInDays = dateDifferenceInDays(earlierEndDate, laterStartDate);
        return overlapInDays;
    }
}