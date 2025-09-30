package ru.demo.secondlessonapplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ru.demo.secondlessonapplication.util.Manager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;

import ru.demo.secondlessonapplication.model.RoleName;

public class WelcomeController implements Initializable {
    @FXML
    private Label RoleNameWelcome;
    @FXML
    private Button ButtonClick;

    private String showRoleName(){
        Manager.currentRole.setRoleId(Manager.currentUser.getRoleId());
        if (Manager.currentRole.getRoleId() == 1) {
            return RoleName.PARTICIPANT.toString();
        }
        if (Manager.currentRole.getRoleId() == 2) {
            return RoleName.MANAGER.toString();
        }
        if (Manager.currentRole.getRoleId() == 3) {
            return RoleName.ADMIN.toString();
        }
        return "Неизвестная роль";
    }

    private String greetUser(){
        String sayHello = "";
        Manager.currentRole.setRoleId(Manager.currentRole.getRoleId());
        if (Manager.currentRole.getRoleId() == 1) {
            sayHello = "Добрый вечер, " + Manager.currentUser.getFirstName() + "!";
        }
        if (Manager.currentRole.getRoleId() == 2) {
            sayHello = "Добрый день, " + Manager.currentUser.getFirstName() + "!";
        }
        if (Manager.currentRole.getRoleId() == 3) {
            sayHello = "Доброе утро, " + Manager.currentUser.getFirstName() + "!";
        }
        return sayHello;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RoleNameWelcome.setText("Вы вошли как " + showRoleName());
    }

    @FXML
    private void ButtonClickAction(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Приветствие");
        alert.setHeaderText(null);
        alert.setContentText(greetUser());

        alert.showAndWait();
    }
}
