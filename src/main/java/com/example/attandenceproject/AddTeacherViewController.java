package com.example.attandenceproject;

import Models.DBUtility;
import Models.Teacher;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class AddTeacherViewController {
    @FXML
    public TextField className;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public Button backBTN;
    @FXML
    public Button submitBTN;

    public void initialize(){
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    SceneChanger.changeScene(mouseEvent, "TeacherListView.fxml", "Teacher List");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void submit(Event event){
        try {
            new Teacher(firstName.getText(), lastName.getText(), className.getText());
            //DBUtility.addNewTeacher();
            System.out.println("Added teacher");
            SceneChanger.changeScene(event, "TeacherListView.fxml", "Teacher List");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
