package todo.app.my;

import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {
    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> taskList;
    @FXML
    private TextArea detailsTextArea;
    @FXML
    private Label deadline;
    @FXML
    private BorderPane mainBorderPane;


    public void initialize() throws Exception {

//        TodoItem item1 = new TodoItem("Merge", "MERGE BINEE, merge bineeeMERGE BINEE, merge bineeeMERGE BINEE, merge bineeeMERGE BINEE, merge bineeeMERGE BINEE, merge bineeeMERGE BINEE, merge bineeeMERGE BINEE, merge bineeeMERGE BINEE, merge bineeeMERGE BINEE, merge bineeeMERGE BINEE, merge bineee", LocalDate.of(2020, 04, 01));
//        TodoItem item2 = new TodoItem("Merge", "merge merge merge", LocalDate.of(2020, 01, 22));
//        TodoItem item3 = new TodoItem("Merge", "merge merge merge", LocalDate.of(2020, 01, 01));
//        TodoItem item4 = new TodoItem("Merge", "merge merge merge", LocalDate.of(2020, 07, 01));
        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        taskList.getItems().setAll(todoItems);
//        // Select the first task when the app is open
//        taskList.getSelectionModel().selectFirst();
//        handleClickListView();
    }

    public void showDialog() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        mainBorderPane.widthProperty();
        dialog.setTitle("Add new TODO item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addTask.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            AddTaskController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processItem();
            todoItems.add(newItem);
            taskList.getItems().setAll(todoItems);
            taskList.getSelectionModel().select(newItem);
            handleClickListView();
        }
    }

    @FXML
    public void handleClickListView() {
            TodoItem item = taskList.getSelectionModel().getSelectedItem();
            detailsTextArea.setText(item.getDetails());
            deadline.setText(item.getDueDate().toString());
    }
    public void deleteTask(){
        TodoItem itemToDelete = taskList.getSelectionModel().getSelectedItem();
        taskList.getItems().remove(itemToDelete);
        handleClickListView();
    }

    public void addTask() {
        TodoData todoData = new TodoData();
        taskList = new ListView<TodoItem>(todoData.getTodoItems());
        System.out.println(taskList.toString());
    }

}








