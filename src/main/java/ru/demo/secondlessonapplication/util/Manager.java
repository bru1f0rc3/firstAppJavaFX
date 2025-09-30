package ru.demo.secondlessonapplication.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ru.demo.secondlessonapplication.model.Role;
import ru.demo.secondlessonapplication.model.User;

import java.util.Optional;

public class Manager {
    public static User currentUser = null;
    public static Role currentRole = new Role();
    public static Stage mainStage = null;

    public static void ShowPopup(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Закрыть приложение");
        alert.setHeaderText("Вы хотите выйти из приложения?");
        alert.setContentText("Все несохраненные данные, будут потеряны");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    public static void ShowErrorMessageBox(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Произошла ошибка");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
