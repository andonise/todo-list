package datamodel;

public class TodoItem {
    private String shortDescription;
    private String details;
    private String dueDate;

//    public TodoItem(String description, String taskDetails, LocalDate pickDate) {
//    }
    public TodoItem() {

    }
    public TodoItem(String shortDescription, String details, String dueDate) {
        this.shortDescription = shortDescription;
        this.details = details;
        this.dueDate = dueDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return shortDescription;
    }


}

