package ToDo.List;

import ToDo.Item.ToDoItem;

import java.util.ArrayList;

public class ToDoList {
    private String title;
    private ToDoItem item;
    private ArrayList<ToDoItem> toDoItemArrayList;

    public ToDoList(String title) {
        this.title = title;
        this.toDoItemArrayList = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ToDoItem getItem() {
        return item;
    }

    public void setItem(ToDoItem item) {
        this.item = item;
    }

    public ArrayList<ToDoItem> getToDoItemArrayList() {
        return toDoItemArrayList;
    }

    public void setToDoItemArrayList(ArrayList<ToDoItem> toDoItemArrayList) {
        this.toDoItemArrayList = toDoItemArrayList;
    }

    public boolean addItemToList(ToDoItem item) {
        if (toDoItemArrayList.contains(item)) {
            return false;
        } else {
            toDoItemArrayList.add(item);
        }
            return true;
    }
}
