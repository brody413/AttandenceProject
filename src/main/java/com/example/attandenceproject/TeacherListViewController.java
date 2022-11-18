package com.example.attandenceproject;

import Models.DBUtility;
import Models.Teacher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


public class TeacherListViewController {
    @FXML
    public Button createTeacherBTN;
    @FXML
    private ListView<Teacher> teacherListView;

    @FXML
    private void initialize() throws SQLException {

        teacherListView.getItems().removeAll();
        teacherListView.getItems().addAll(DBUtility.getAllTeachers());
        teacherListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(new Object(){}.getClass().getResource("StudentListView.fxml"));
                    Parent root = loader.load();
                    StudentListViewController studentController = loader.getController();
                    studentController.setTeacher(teacherListView.getSelectionModel().getSelectedItem());
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        createTeacherBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    SceneChanger.changeScene(mouseEvent, "AddTeacherView.fxml", "Add Teacher");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}