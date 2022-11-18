package com.example.attandenceproject;

import Models.DBUtility;
import Models.Student;
import Models.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddStudentViewController {
    @FXML
    public ComboBox teacherDropdown;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    int teacherID = 0;

    public void initialize() throws SQLException {
        ArrayList<Teacher> teachers = DBUtility.getAllTeachers();
        teacherDropdown.getItems().removeAll();
        teacherDropdown.getItems().addAll(teachers);
    }

    public void submit(ActionEvent event) throws IOException {
        if(teacherDropdown.getSelectionModel().getSelectedItem() == null || firstName.getText().trim().equals("") || lastName.getText().trim().equals("")) return;
        new Student(firstName.getText(), lastName.getText(), new boolean[]{false,false,false,false,false}, (Teacher) teacherDropdown.getSelectionModel().getSelectedItem());
        SceneChanger.changeScene(event, "TeacherListView.fxml", "Teacher List");
    }

    public void back(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "TeacherListView.fxml", "Teacher List");
    }

    public void selectTeacher(Teacher teacher){
        teacherID = teacher.getId();
    }
}
