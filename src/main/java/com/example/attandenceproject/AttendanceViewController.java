package com.example.attandenceproject;

import Models.DBUtility;
import Models.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AttendanceViewController {
    @FXML
    public Label studentNameLBL;
    @FXML
    public ToggleGroup mondayGroup;
    @FXML
    public ToggleGroup tuesdayGroup;
    @FXML
    public ToggleGroup wednesdayGroup;
    @FXML
    public ToggleGroup thursdayGroup;
    @FXML
    public ToggleGroup fridayGroup;
    @FXML
    public Button submitFormBTN;
    @FXML
    public Button teacherBTN;
    public Student student;

    @FXML
    void initialize(){
        mondayGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                String selectedText = ((RadioButton) newToggle).getText();
                if (selectedText.equals("Yes")) student.setPresent(0, true);
                else student.setPresent(0, false);
            }
        });
        tuesdayGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                String selectedText = ((RadioButton) newToggle).getText();
                if (selectedText.equals("Yes")) student.setPresent(1, true);
                else student.setPresent(1, false);
            }
        });
        wednesdayGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                String selectedText = ((RadioButton) newToggle).getText();
                if (selectedText.equals("Yes")) student.setPresent(2, true);
                else student.setPresent(2, false);
            }
        });
        thursdayGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                String selectedText = ((RadioButton) newToggle).getText();
                if (selectedText.equals("Yes")) student.setPresent(3, true);
                else student.setPresent(3, false);
            }
        });
        fridayGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                String selectedText = ((RadioButton) newToggle).getText();
                if (selectedText.equals("Yes")) student.setPresent(4, true);
                else student.setPresent(4, false);
            }
        });
        teacherBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    SceneChanger.changeScene(mouseEvent, "TeacherListView.fxml", "Attendance");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        submitFormBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    DBUtility.updateStudentAttendance(student);

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(new Object(){}.getClass().getResource("StudentListView.fxml"));
                    Parent root = loader.load();
                    StudentListViewController studentController = loader.getController();
                    studentController.setTeacher(student.getTeacher());
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setStudent(Student student) {
        this.student = student;
        studentNameLBL.setText(String.format("%s %s", student.getFirstName(), student.getLastName()));
        mondayGroup.selectToggle(student.isPresent()[0]?mondayGroup.getToggles().get(0):mondayGroup.getToggles().get(1));
        tuesdayGroup.selectToggle(student.isPresent()[1]?tuesdayGroup.getToggles().get(0):tuesdayGroup.getToggles().get(1));
        wednesdayGroup.selectToggle(student.isPresent()[2]?wednesdayGroup.getToggles().get(0):wednesdayGroup.getToggles().get(1));
        thursdayGroup.selectToggle(student.isPresent()[3]?thursdayGroup.getToggles().get(0):thursdayGroup.getToggles().get(1));
        fridayGroup.selectToggle(student.isPresent()[4]?fridayGroup.getToggles().get(0):fridayGroup.getToggles().get(1));
    }
}
