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

public class WelcomeController implements Initializable {
    @FXML
    private Label RoleNameWelcome;
    @FXML
    private Button ButtonClick;

    private String showRoleName(){
        Manager.currentRole.setRoleId(Manager.currentUser.getRoleId());
        return Manager.currentRole.getTitle();
    }

    private String greetUser(){
        String greeting = switch (Manager.currentRole.getRoleId().intValue()) {
            case 1 -> "Добрый вечер";
            case 2 -> "Добрый день";
            case 3 -> "Доброе утро";
            default -> "Здравствуйте";
        };
        return greeting + ", " + Manager.currentUser.getFirstName() + "!";
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