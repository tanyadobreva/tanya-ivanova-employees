import java.util.Date;

public class Employee {

private Integer empID;
private Integer project;
private Date startDate;
private Date endDate;

    public Employee (Integer empID, Integer project, Date startDate, Date endDate) {

        this.empID = empID;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getEmpID() {
        return empID;
    }

    public Integer getProject() {
        return project;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public void setProject(Integer project) {
        this.project = project;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
