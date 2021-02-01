package todo.app.my;

import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


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
        String pickDate = dueDate.getValue().toString();

        TodoItem newItem = new TodoItem(description, taskDetails, pickDate);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }

}






