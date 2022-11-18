package Models;

public class Student {

    private int id;
    private String firstName, lastName;
    Teacher teacher;

    boolean[] isPresent;

    public Student(int id, String firstName, String lastName, boolean[] isPresent, Teacher teacher) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setTeacher(teacher);
        setPresent(isPresent);
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

    public boolean[] isPresent() {
        return isPresent;
    }

    public void setPresent(boolean[] present) {
        isPresent = present;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
