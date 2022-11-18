package Models;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Predicate;

public class DBUtility {
    //set the username and password for accessing sql tables-files
    private static String user = "root";
    private static String password = "1234";
    private static String connectionString = "jdbc:mysql://127.0.0.1/attendance";

    /**
     * Adds a Teacher to the database
     *
     * @param newTeacher The Teacher to be added
     * @return the ID of the Teacher added
     * @throws SQLException
     */
    public static int addNewTeacher(Teacher newTeacher) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int teacherId = -1;
        try {
            //1. connect to the database, set the name for the sql db
            conn = DriverManager.getConnection(connectionString,
                    user, password);

            //2. Create SQL String
            String sql = "INSERT INTO teachers (firstName, lastName, className) " +
                    "VALUES (?,?,?)";

            //3. prepare the query with the SQL
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //4. bind the values to the datatypes
            preparedStatement.setString(1, newTeacher.getFirstName());
            preparedStatement.setString(2, newTeacher.getLastName());
            preparedStatement.setString(3, newTeacher.getClassName());

            //5. execute the insert
            preparedStatement.executeUpdate();

            //6. get the Teacher ID
            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next()) {
                teacherId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //The finally block will ALWAYS run whether an exception is triggered or not
        finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (conn != null)
                conn.close();
            return teacherId;
        }
    }

    public static int addNewStudent(Student newStudent) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int studentId = -1;
        try {
            //1. connect to the database, set the name for the sql db
            conn = DriverManager.getConnection(connectionString,
                    user, password);

            //2. Create SQL String
            String sql = "INSERT INTO students (firstName, lastName, teacherID, isPresent) " +
                    "VALUES (?,?,?,?)";

            //3. prepare the query with the SQL
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //4. bind the values to the datatypes
            preparedStatement.setString(1, newStudent.getFirstName());
            preparedStatement.setString(2, newStudent.getLastName());
            preparedStatement.setInt(3, newStudent.getTeacher().getId());

            //4.1. convert newStudent.isPresent to string representation
            String presentStr = "";
            for (boolean present : newStudent.isPresent()) {
                presentStr += present ? "1 " : "0 ";
            }
            preparedStatement.setString(4, presentStr.trim());

            //5. execute the insert
            preparedStatement.executeUpdate();

            //6. get the Student ID
            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next()) {
                studentId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //The finally block will ALWAYS run whether an exception is triggered or not
        finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (conn != null)
                conn.close();
            return studentId;
        }
    }

    public static ArrayList<Teacher> getAllTeachers() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet resultSet = null;

        try {
            //1. connect to the DB
            conn = DriverManager.getConnection(connectionString,
                    user, password);

            //2. create a statement object
            sqlStatement = conn.createStatement();

            //3. create the SQL query
            resultSet = sqlStatement.executeQuery("SELECT * FROM teachers");

            //4. loop over the result set and create Teacher objects
            while (resultSet.next()) {
                Teacher newTeacher = new Teacher(resultSet.getInt("ID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("className"));
                teachers.add(newTeacher);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
            if (resultSet != null) resultSet.close();
            if (sqlStatement != null) sqlStatement.close();
            return teachers;
        }
    }

    public static ArrayList<Student> getAllStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet resultSet = null;

        try {
            //1. connect to the DB
            conn = DriverManager.getConnection(connectionString,
                    user, password);

            //2. create a statement object
            sqlStatement = conn.createStatement();

            //3. create the SQL query
            resultSet = sqlStatement.executeQuery("SELECT * FROM students");

            //4. loop over the result set and create Student objects
            ArrayList teachers = getAllTeachers();
            while (resultSet.next()) {
                String[] presentStr = resultSet.getString("isPresent").split(" ");
                boolean[] present = {false, false, false, false, false};
                for (int i = 0; i < presentStr.length; i++) {
                    present[i] = Integer.parseInt(presentStr[i]) == 1;
                }

                int teacherID = resultSet.getInt("teacherID");
                Teacher teacher = (Teacher) teachers.stream().filter(new Predicate() {
                    @Override
                    public boolean test(Object o) {
                        return ((Teacher) o).getId() == teacherID;
                    }
                }).findFirst().get();

                Student newStudent = new Student(resultSet.getInt("ID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        present,
                        teacher);
                students.add(newStudent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
            if (resultSet != null) resultSet.close();
            if (sqlStatement != null) sqlStatement.close();
            return students;
        }
    }

    public static void updateStudentAttendance(Student student) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            //1. connect to the database, set the name for the sql db
            conn = DriverManager.getConnection(connectionString,
                    user, password);

            //2. Create SQL String
            String sql = "UPDATE students SET isPresent = ? WHERE ID = ?";

            //3. prepare the query with the SQL
            preparedStatement = conn.prepareStatement(sql);

            //4. bind the values to the datatypes
            preparedStatement.setInt(2, student.getId());

            //4.1. convert newStudent.isPresent to string representation
            String presentStr = "";
            for (boolean present : student.isPresent()) {
                presentStr += present ? "1 " : "0 ";
            }
            preparedStatement.setString(1, presentStr.trim());

            //5. execute the insert
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (conn != null)
                conn.close();
        }
    }

}