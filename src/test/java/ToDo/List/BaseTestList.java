package ToDo.List;

import ToDo.Item.ToDoItem;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class BaseTestList {
    protected ToDoList toDoList;
    protected String title;
    protected ToDoItem item1;
    protected ToDoItem item2;
    protected ToDoItem item3;
    protected ToDoItem item4;

    @BeforeEach
    public void creatingToDoList() {
        title = "List of tasks";
        toDoList = ToDoList.of(title);
    }

}
