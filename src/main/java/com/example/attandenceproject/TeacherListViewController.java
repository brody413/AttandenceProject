package com.example.attandenceproject;

import Models.DBUtility;
import Models.Teacher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.attandenceproject.StudentListViewController.teacher;

public class TeacherListViewController {
    @FXML
    private ListView<Teacher> teacherListView;

    public TeacherListViewController() {
    }

    @FXML
    private void initialize() throws SQLException {

        teacherListView.getItems().removeAll();
        teacherListView.getItems().addAll(DBUtility.getAllTeachers());


        teacherListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Teacher>() {
            @Override
            public void changed(ObservableValue<? extends Teacher> observableValue, Teacher oldTeacher, Teacher newTeacher) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentListView.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(loader.load()));
                    StudentListViewController studentController = loader.getController();
                    studentController.setTeacher(newTeacher);
                    stage.show();

                } catch (IOException e){

                }
            }
        });
    }
}