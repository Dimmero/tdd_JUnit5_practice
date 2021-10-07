package ToDo.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BasicToDoListPropertiesTest {
    public static ToDoList toDoList;
    public static String title = "List of tasks";

    public ToDoList creatingToDoList() {
        return toDoList = new ToDoList(title);
    }

    @Test
    public void listShouldBeEmptyAfterCreation() {
        int expectedSize = 0;
        creatingToDoList();
        Assertions.assertEquals(expectedSize, toDoList.getToDoItemArrayList().size());
    }

    @Test
    public void listHasTitleAndSize() {
        String invalidTitle = "";
        creatingToDoList();
        Assertions.assertNotEquals(invalidTitle, toDoList.getTitle());
        Assertions.assertNotNull(toDoList.getToDoItemArrayList());
    }



}
