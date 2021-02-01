package todo.app.my;

import datamodel.Datasource;
import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Controller {
    private List<TodoItem> todoItems;

    private List<TodoItem> todaysItems;

    @FXML
    private ListView<TodoItem> taskList;
    @FXML
    private TextArea detailsTextArea;
    @FXML
    private Label deadline;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ToggleButton toggleButton;


    public void initialize() throws Exception {
        todoItems = new ArrayList<TodoItem>();
        try {
            Datasource data = new Datasource();
            data.open();
            List<TodoItem> todoItem = new ArrayList<>();
            todoItem = data.querryTasks();
            if (todoItem != null) {
                taskList.getItems().setAll(data.querryTasks());
            } else {
                System.out.println("You don't have any tasks to do !");
            }
        } catch (Exception e) {
            System.out.println("Could not query the tasks " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showDialog() throws IOException, SQLException {
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
            TodoData.getInstance().addTodoItem(newItem);

            try {
                Statement statement = Datasource.conn.createStatement();
                statement.executeUpdate("INSERT INTO task (Description, Details, Date, Done) " + "VALUES (\'" + newItem.getShortDescription() + "\', \'" + newItem.getDetails() + "\', \' " + newItem.getDueDate() + "\', 1 )");

            } catch (Exception e) {
                System.out.println("Couldn't establish the connection with the database !");
                e.printStackTrace();
            }

            taskList.getItems().add(newItem);
            taskList.getSelectionModel().select(newItem);
            handleClickListView();
//            Datasource.conn.close();

            System.out.println(todoItems.toString());
        }
    }

    @FXML
    public void handleClickListView() {
            TodoItem item = taskList.getSelectionModel().getSelectedItem();
            detailsTextArea.setText(item.getDetails());
            deadline.setText(item.getDueDate());
            }

//    public List todaysTasks () {
//        boolean isSelected = toggleButton.isSelected();
//        if (isSelected) {
//            todaysItems = new ArrayList<>();
//            for (TodoItem item : todoItems) {
//                if (item.getDueDate() == LocalDate.now().toString());
//                todaysItems.add(item);
//            }
//            try {
//           taskList.getItems().setAll(todaysItems); } catch (Exception e) {
//                System.out.println("Nu sunt taskuri pentru ziua de astazi !");
//            }
//            return todaysItems;
//        }
//            return null;

//        }




    public void deleteTask(){
        TodoItem itemToDelete = taskList.getSelectionModel().getSelectedItem();

        try {
            Statement statement = Datasource.conn.createStatement();
            statement.execute("DELETE FROM task " +
                    "WHERE Description= \'" + itemToDelete.getShortDescription() + "\' AND Details= \'" + itemToDelete.getDetails() +
                    "\' AND Date= \'" + itemToDelete.getDueDate() + "\';");

        } catch (Exception e) {
            System.out.println("Couldn't delete the task from database");
            e.printStackTrace();
        }

        taskList.getItems().remove(itemToDelete);
        handleClickListView();
    }
    public void todaysTask() {
        for (TodoItem item : todoItems) {
            if(item.getDueDate() == LocalDate.now().toString());
            taskList.getItems().add(item);
        }

    }

}








