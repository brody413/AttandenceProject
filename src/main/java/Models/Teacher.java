package Models;
import java.sql.SQLException;
public class Teacher {

    private int id;
    private String firstName, lastName, className;


    public Teacher(int id, String firstName, String lastName, String className) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setClassName(className);
    }

    public Teacher(String firstName, String lastName, String className){
        setFirstName(firstName);
        setLastName(lastName);
        setClassName(className);

        try {
            id = DBUtility.addNewTeacher(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
