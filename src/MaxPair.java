public class MaxPair {

    private Integer emp1;
    private Integer emp2;
    private Integer project;

    public void setProject(Integer project) {
        this.project = project;
    }

    public Integer getProject() {
        return project;
    }

    private long numOfDaysTogether;
    public MaxPair(Integer emp1, Integer emp2, Integer proj, long numDays){
        this.emp1 = emp1;
        this.emp2 = emp2;
        this.project = proj;
        this.numOfDaysTogether = numDays;
    }

    public void setEmp1(Integer emp1) {
        this.emp1 = emp1;
    }

    public void setEmp2(Integer emp2) {
        this.emp2 = emp2;
    }

    public void setNumOfDaysTogether(long numOfDaysTogether) {
        this.numOfDaysTogether = numOfDaysTogether;
    }

    public Integer getEmp1() {
        return emp1;
    }

    public Integer getEmp2() {
        return emp2;
    }

    public long getNumOfDaysTogether() {
        return numOfDaysTogether;
    }
}
