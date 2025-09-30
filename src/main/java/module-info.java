module ru.demo.secondlessonapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;
    requires javafx.swing;
    opens ru.demo.secondlessonapplication to javafx.fxml;
    opens ru.demo.secondlessonapplication.model to org.hibernate.orm.core;
    exports ru.demo.secondlessonapplication;
    exports ru.demo.secondlessonapplication.controller;
    opens ru.demo.secondlessonapplication.controller to javafx.fxml;
}