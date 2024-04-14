package by.maksim.curse_work.model;

public class Employee {
    protected Integer id, accessLevel;
    protected String surname, name, patronymic, jobTitle, cardCondition;

    public Employee(){
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCardCondition(String cardCondition) {
        this.cardCondition = cardCondition;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCardCondition() {
        return cardCondition;
    }
}
