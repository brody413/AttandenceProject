package Models;

public class Student {

    private int id;
    private String firstName, lastName, teacher;

    boolean isPresent, isNotPresent;

    public Student(int id, String firstName, String lastName, boolean isPresent, boolean isNotPrsent, String teacher) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setTeacher(teacher);
        setPresent(isPresent);
        setNotPresent(isNotPresent);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public boolean isNotPresent() {
        return isNotPresent;
    }

    public void setNotPresent(boolean notPresent) {
        isNotPresent = notPresent;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}
