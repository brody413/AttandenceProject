package com.example.attandenceproject;

import Models.DBUtility;
import Models.Student;
import Models.Teacher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StudentListViewController {

    @FXML
    private ListView<Student> studentListView;
    @FXML
    private Button backToMainMenuBTN;


    @FXML
    private void initialize() {
        backToMainMenuBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    SceneChanger.changeScene(mouseEvent, "TeacherListView.fxml", "Attendance");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        studentListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(new Object(){}.getClass().getResource("AttendanceView.fxml"));
                    Parent root = loader.load();
                    AttendanceViewController attendanceViewController = loader.getController();
                    attendanceViewController.setStudent(studentListView.getSelectionModel().getSelectedItem());
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setTeacher(Teacher teacher) {
        try {
            studentListView.getItems().removeAll();
            studentListView.getItems().addAll(DBUtility.getAllStudents().stream().filter(student -> student.getTeacher().getId() == teacher.getId()).toList());
        } catch (SQLException e){

        }
    }

}
