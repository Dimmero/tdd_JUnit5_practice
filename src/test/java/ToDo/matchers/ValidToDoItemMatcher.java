package ToDo.matchers;

import ToDo.Item.ToDoItem;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ValidToDoItemMatcher extends TypeSafeMatcher<ToDoItem> {

    private String title;
    private String description;

    protected ValidToDoItemMatcher(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    protected boolean matchesSafely(ToDoItem toDoItem) {
        return toDoItem.getTitle().equals(title) & toDoItem.getDescription().equals(description);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Valid item with title and description");
    }

    public static Matcher<ToDoItem> isValidToDoItem(String title, String description) {
        return new ValidToDoItemMatcher(title, description);
    }
}
