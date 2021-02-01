package datamodel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "todolist.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\JavaMasterclass\\Task\\" + DB_NAME;
    public static final String TABLE_TASK = "task";
    public static final String COLUMN_TASK_DATE = "Date";

    public static Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database" + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close connection " + e.getMessage());
        }
    }

    public List<TodoItem> querryTasks() {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_TASK);
        sb.append(" ORDER BY ");
        sb.append(COLUMN_TASK_DATE);
        sb.append(" DESC");

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            List<TodoItem> todoItems = new ArrayList<>();

            while (results.next()) {
                TodoItem todoItem = new TodoItem();
                todoItem.setShortDescription(results.getString(2));
                todoItem.setDetails(results.getString(3));
                todoItem.setDueDate(results.getString(4));
                todoItems.add(todoItem);
            }
            return todoItems;
        } catch (SQLException e) {
            System.out.println("Query failed" + e.getMessage());
            return null;
        }
    }
//
//    public LocalDateTime convertToLocalDate (Date dateToConvert) {
//        return dateToConvert.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDateTime();
//    }
}
