module Task {
    requires javafx.fxml;
    requires javafx.controls;

    opens todo.app.my;
    opens datamodel;
}