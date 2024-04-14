package by.maksim.curse_work.model;

public class Device {
    protected String designation, condition;
    protected Integer id, number;

    public Device(){
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDesignation() {
        return designation;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getId() {
        return id;
    }

    public String getCondition() {
        return condition;
    }
}
