package ru.demo.secondlessonapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.demo.secondlessonapplication.model.User;
import ru.demo.secondlessonapplication.util.Manager;

import java.io.IOException;

public class LoginApp extends Application {
    public User currentUser;

    public static void main(String[] args) {
        launch();


    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setScene(getNewScene());
        stage.setTitle("Авторизация!");
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            Manager.ShowPopup();
        });
        stage.setOnShown(windowEvent -> {
            try {
                stage.setScene(getNewScene());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Manager.mainStage = stage;
        stage.show();
    }

    Scene getNewScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApp.class.getResource("login-view.fxml"));
        return new Scene(fxmlLoader.load());
    }
}
