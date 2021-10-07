package ToDo.Item;

import ToDo.ToDoArgumentsProvider;
import ToDo.exceptions.ToDoItemValidationException;
import ToDo.exceptions.TooLongOrNullDescriptionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static ToDo.matchers.ValidToDoItemMatcher.isValidToDoItem;
import static org.junit.jupiter.api.Assertions.*;


public class BasicTodoItemPropertiesTest extends BaseTest {
    Logger logger = LoggerFactory.getLogger(BasicTodoItemPropertiesTest.class);

    public String createStringWith251Length() {
        StringBuilder descriptionOf251Characters = new StringBuilder();
        while (descriptionOf251Characters.length() < 251) {
            descriptionOf251Characters.append(1);
        }
        return descriptionOf251Characters.toString();
    }

    @ParameterizedTest
    @CsvFileSource(files = {"C:\\Users\\Dimmer\\Desktop\\TrustMe\\tdd_JUnit5_practice\\src\\main\\resources\\todos.csv"}, numLinesToSkip = 1)
    public void shouldCreateAValidToDoItemsCsvFileSource(String title, String description) {
        ToDoItem item = ToDoItem.of(title, description);
        assertThat(item, isValidToDoItem(title, description));
    }

    @ParameterizedTest
    @ArgumentsSource(ToDoArgumentsProvider.class)
    public void shouldCreateAValidToDoItemsArgumentsClass(String title, String description) {
        ToDoItem item = ToDoItem.of(title, description);
        assertThat(item, isValidToDoItem(title, description));
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsClass.class)
    public void shouldCreateAValidToDoItemsStaticClass(String title, String description) {
        ToDoItem item = ToDoItem.of(title, description);
        assertThat(item, isValidToDoItem(title, description));
    }

    static class MyArgumentsClass implements ArgumentsProvider{
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Arguments.arguments("Item 1", "Description 1"),
                    Arguments.arguments("Item 2", "Description 2"),
                    Arguments.arguments("Item 3", "Description 3")
            );
        }
    }

    @Test
    public void shouldCreateTodoItemWithTitleAndDescriptionHamcrest() {
        assertThat(item, isValidToDoItem(title, description));
    }

    @Tag("Happy")
    @Test
    public void shouldCreateTodoItemWithTitleAndDescription() {
//        LOGGER
//        logger.warn("This is the test log string");
        Assertions.assertEquals(title, item.getTitle());
        Assertions.assertEquals(description, item.getDescription());
    }

    @Tag("Happy")
    @DisplayName("Custom name for our test")
    @Test
    public void shouldThrowAnExceptionWhileCreatingItemWithEmptyTitle() {
        String invalidTitle = "";
        Assertions.assertThrows(ToDoItemValidationException.class,
                () -> ToDoItem.of(invalidTitle, description));
    }

    @Test
    public void testPredicate() {
        System.out.println("");

        Assertions.assertThrows(ToDoItemValidationException.class, () -> ToDoItem.of("", description));
        Assertions.assertThrows(ToDoItemValidationException.class, () -> ToDoItem.of(null, description));
    }

    @Test
    public void shouldThrowAnExceptionWhileSettingAnEmptyTitle() {
        String invalidTitle = "";
        Assertions.assertThrows(ToDoItemValidationException.class, () -> item.setTitle(invalidTitle));
    }

    @Test
    public void shouldToggleStatusFromPendingToInProgress() {
        item.toggleStatus();
        Assertions.assertEquals(ItemStatus.IN_PROGRESS, item.getStatus());
    }

    @Test
    public void shouldToggleStatusFromInProgressToPending() {
        item.toggleStatus();
        item.toggleStatus();
        Assertions.assertEquals(ItemStatus.PENDING, item.getStatus());
    }

    @Test
    public void shouldCompleteATaskRepresentedByItem() {
        item.toggleStatus();
        item.complete();
        Assertions.assertEquals(ItemStatus.COMPLETED, item.getStatus());
    }

    @Test
    public void shouldNotToggleStatusFromCompleteToInProgress() {
        item.toggleStatus();
        item.complete();
        item.toggleStatus();
        Assertions.assertNotEquals(ItemStatus.IN_PROGRESS, item.getStatus());
    }

    @Test
    public void shouldToggleStatusFromCompleteToInProgress() {
        item.biDirectionalToggleStatus();
        item.complete();
        item.biDirectionalToggleStatus();
        Assertions.assertEquals(ItemStatus.IN_PROGRESS, item.getStatus());
    }

    @Test
    public void shouldNotCreateATodoItemWithDescriptionLongerThan250Chars() {
        Assertions.assertThrows(TooLongOrNullDescriptionException.class, () -> ToDoItem.of(title, createStringWith251Length()));
    }

    @Test
    public void shouldNotCreateATodoItemWithNullDescription() {
        String emptyDescription = "";
        Assertions.assertThrows(TooLongOrNullDescriptionException.class, () -> ToDoItem.of(title, emptyDescription));
    }

    @Test
    public void shouldNotSetANewDescriptionLongerThan250Chars() {
        Assertions.assertThrows(TooLongOrNullDescriptionException.class, () -> item.setDescription(createStringWith251Length()));
    }

    @Test
    public void shouldNotSetAnNullNewDescription() {
        String invalidDescription = "";
        Assertions.assertThrows(TooLongOrNullDescriptionException.class, () -> item.setDescription(invalidDescription));
    }
}

