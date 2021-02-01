module Task {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens todo.app.my;
    opens datamodel;
}