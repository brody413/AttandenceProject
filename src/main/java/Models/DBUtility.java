package Models;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    //set the username and password for accessing sql tables-files
    private static String user = "";
    private static String password = "";

    public static int addNewTeacher(Teacher newTeacher) throws SQLException{
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int teacherId = -1;
        try{
            //1. connect to the database, set the name for the sql db
            conn = DriverManager.getConnection("jdbc:mysql:// name of the sql (needs to be created)",
                    user, password);
            //2. Create SQL String
            String sql = "INSERT INTO teachers (firstName, lastName, className) " +
                    "VALUES (?,?,?,?,?)";

            //3. prepare the query with the SQL
            preparedStatement = conn.prepareStatement(sql, new String[] {"teacherId"});

            //4. bind the values to the datatypes
            preparedStatement.setString(1, newTeacher.getFirstName());
            preparedStatement.setString(2, newTeacher.getLastName());
            preparedStatement.setString(3, newTeacher.getClassName());

            //5. execute the insert
            preparedStatement.executeUpdate();

            //6. get the patient ID
            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next())
            {
                teacherId = rs.getInt(1);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        //The finally block will ALWAYS run whether an excpetion is triggered or not
        finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (conn != null)
                conn.close();
            return teacherId;
        }
    }

    public static ArrayList<Teacher> getAllPatients() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql:// name of the sql (needs to be created)",
                    user, password);

            //2. create a statement object
            sqlStatement = conn.createStatement();

            //3. create the SQL query
            resultSet = sqlStatement.executeQuery("SELECT * FROM teachers");

            //4. loop over the result set and create Patient objects
            while (resultSet.next())
            {
                Teacher newTeacher = new Teacher(resultSet.getInt("teacherId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("className"));
                teachers.add(newTeacher);
            }

        }catch ( Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (conn !=null) conn.close();
            if (resultSet != null) resultSet.close();
            if (sqlStatement != null) resultSet.close();
            return teachers;
        }
    }
}
