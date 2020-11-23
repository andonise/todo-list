package todo.app.my;

import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;


public class AddTaskController {
    @FXML
    private TextField shortDescription;
    @FXML
    private TextArea details;
    @FXML
    private DatePicker dueDate;

    public TodoItem processItem() {
        String description = shortDescription.getText().trim();
        String taskDetails = details.getText();
        LocalDate pickDate = dueDate.getValue();

        TodoItem newItem = new TodoItem(description, taskDetails, pickDate);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }

    @FXML
    public void quitDialog(ActionEvent e) {
        ((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
    }

    @FXML
    public void saveDialog(ActionEvent event) throws IOException {
        processItem();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoList.fxml"));
        Controller controller = new Controller();
        controller.addTask();
    }

}






