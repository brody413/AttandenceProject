package com.example.attandenceproject;

import Models.DBUtility;
import Models.Student;
import Models.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StudentListViewController {
    static Teacher teacher;

    @FXML private ListView studentListView;
    @FXML private Button backToMainMenuButton;
    public StudentListViewController() {
    }

    @FXML
    private void initialize() throws SQLException {
        studentListView.getItems().removeAll();
        studentListView.getItems().addAll(DBUtility.getAllStudents().stream().filter(student -> {
            return student.getTeacher().equals(teacher);
        }).toList());
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
        System.out.println("set teacher to " + teacher);
    }

}
