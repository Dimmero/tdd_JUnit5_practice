package ToDo.Item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.function.Predicate;

public class BaseTest {
    protected String title;
    protected String description;
    protected ToDoItem item;


    @BeforeEach
    public void creatingToDoObject() {
        title = "Complete Java Udemy course";
        description = "Ivan said to do it quickly";
        item = ToDoItem.of(title, description);
    }

    @AfterEach
    public void deleteToDoObject() {
        item = null;
    }
}
