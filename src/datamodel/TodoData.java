package datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodoData {
    private static TodoData instance = new TodoData();

    private ObservableList<TodoItem> todoItems = FXCollections.observableArrayList();

    public TodoData() {
    }

    public static TodoData getInstance() {
        return instance;
    }

    public void addTodoItem(TodoItem item) {
        todoItems.setAll(item);
    }

    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }


}
