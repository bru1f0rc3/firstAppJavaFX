package ru.demo.secondlessonapplication.controller;

import jakarta.persistence.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.hibernate.Session;
import ru.demo.secondlessonapplication.LoginApp;
import ru.demo.secondlessonapplication.model.User;
import ru.demo.secondlessonapplication.util.HibernateSessionFactoryUtil;
import ru.demo.secondlessonapplication.util.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static ru.demo.secondlessonapplication.util.Manager.ShowErrorMessageBox;

public class LoginController implements Initializable{
    @FXML
    RowConstraints ThirdRow;
    @FXML
    private Button BtnOk;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private TextField TextFieldUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initController();
    }

    @FXML
    void BtnOkActon(ActionEvent event) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User u", User.class);
            List<User> users = query.getResultList();
            Optional<User> person = users.stream().filter(user -> user.getUsername().equals(TextFieldUsername.getText()) &&
                    user.getPassword().equals(PasswordField.getText())).findFirst();

            if (person.isEmpty()) {
                System.out.println("Bad error");
                ShowErrorMessageBox("Не верный логин, пароль");
                return;
            }
            person.ifPresent(this::showMainWindow);
        }
        catch (Exception e){
            System.out.println("Исключение " + e);
        }
    }

    private String showStage(){
        Manager.currentRole.setRoleId(Manager.currentUser.getRoleId());
        if (Manager.currentRole.getRoleId() == 1) {
            return "user-view.fxml";
        }
        if (Manager.currentRole.getRoleId() == 2) {
            return "manager-view.fxml";
        }
        if (Manager.currentRole.getRoleId() == 3) {
            return "admin-view.fxml";
        }
        return "user-view.fxml"; // значение по умолчанию
    }

    public void showMainWindow(User person) {
        Manager.currentUser = person;
        System.out.println(Manager.currentUser);
        Manager.mainStage.hide();
        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApp.class.getResource(showStage()));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Вы вошли как " + Manager.currentUser.getFirstName());
        newWindow.setScene(scene);
        newWindow.setOnCloseRequest(e -> {
            Manager.mainStage.show();
        });

        newWindow.show();
    }

    public void initController()
    {
        ThirdRow.setPrefHeight(0);
    }
}
