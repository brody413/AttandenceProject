package Models;

public class Student {

    private int id;
    private String firstName, lastName, teacher;

    public Student(int id, String firstName, String lastName, String teacher) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setTeacher(teacher);
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}
