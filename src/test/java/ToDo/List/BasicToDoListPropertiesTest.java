package ToDo.List;

import ToDo.Item.BaseTest;
import ToDo.Item.ItemStatus;
import ToDo.Item.ToDoItem;
import ToDo.exceptions.DuplicateItemException;
import ToDo.exceptions.ToDoItemValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BasicToDoListPropertiesTest extends BaseTestList {

    @Test
    public void listShouldBeEmptyAfterCreation() {
        Assertions.assertTrue(toDoList.getToDoItemArrayList().isEmpty());
    }

    @Test
    public void listHasTitleAndSize() {
        Assertions.assertFalse(toDoList.getTitle().isBlank());
        Assertions.assertNotNull(toDoList.getToDoItemArrayList());
    }

    @Test
    public void testListHasSize0AfterCreationAndIncreasesAfterAddingItems() {
        item1 = ToDoItem.of("Item1", "Description1");
        item2 = ToDoItem.of("Item2", "Description2");
        toDoList.addItemToList(item1);
        toDoList.addItemToList(item2);
        Assertions.assertEquals(2, toDoList.getToDoItemArrayList().size());
    }

    @Test
    public void testCombiningListsIntoOne() {
        item1 = ToDoItem.of("Item1", "Description1");
        item2 = ToDoItem.of("Item2", "Description2");
        toDoList.addItemToList(item1);
        toDoList.addItemToList(item2);

        ToDoList secondList = ToDoList.of("Second list");
        item3 = ToDoItem.of("Item3 for test", "Description3 for test");
        item4 = ToDoItem.of("Item4 for test", "Description4 for test");
        secondList.addItemToList(item3);
        secondList.addItemToList(item4);
        toDoList.combineListsIntoOne("Two merged Lists", toDoList, secondList);
        Assertions.assertEquals(4, toDoList.getToDoItemArrayList().size());
    }

    @Test
    public void testCombiningListsIntoOneWithNewTitle() {
        item1 = ToDoItem.of("Item1", "Description1");
        item2 = ToDoItem.of("Item2", "Description2");
        toDoList.addItemToList(item1);
        toDoList.addItemToList(item2);
        ToDoList secondList = new ToDoList("Second list");
        ToDoItem item3 = new ToDoItem("Item3 for test", "Description3 for test");
        ToDoItem item4 = new ToDoItem("Item4 for test", "Description4 for test");
        secondList.addItemToList(item3);
        secondList.addItemToList(item4);
        String newTitle = "Two merged Lists";
        toDoList.combineListsIntoOne(newTitle, toDoList, secondList);
        Assertions.assertEquals(newTitle, toDoList.getTitle());
    }

    @Test
    public void testListWithEmptyTitle() {
        String invalidTitle = "";
        Assertions.assertThrows(ToDoItemValidationException.class, () -> ToDoList.of(invalidTitle));
    }

    @Test
    public void testAddingItemToList() {
        item1 = ToDoItem.of("Item1", "Description1");
        toDoList = ToDoList.of("List");
        Assertions.assertTrue(toDoList.addItemToList(item1));
    }

    @Test
    public void testRemoveItemByString() {
        String itemTitle = "Item1";
        item1 = ToDoItem.of(itemTitle, "Description1");
        toDoList.addItemToList(item1);
        Assertions.assertTrue(toDoList.removeItem(itemTitle));
    }

    @Test
    public void testFilterItemsByStatus() {
        item1 = ToDoItem.of("Title1", "Description1");
        item2 = ToDoItem.of("Title2", "Description");
        item1.toggleStatus();
        item2.toggleStatus();
        toDoList.addItemToList(item1);
        toDoList.addItemToList(item2);
        toDoList.filterByStatus(ItemStatus.IN_PROGRESS);
        Assertions.assertEquals(2, toDoList.getFilteredList().size());
    }

    @Test
    public void testItemsSortedByTitle() {
        item1 = ToDoItem.of("A title", "Description1");
        item2 = ToDoItem.of("B title", "Description2");
        toDoList.addItemToList(item2);
        toDoList.addItemToList(item1);
        ToDoList toDoList1 = ToDoList.of("New List");
        toDoList1.addItemToList(item1);
        toDoList1.addItemToList(item2);
        Assertions.assertArrayEquals(toDoList.sortByTitle().toArray(), toDoList1.getToDoItemArrayList().toArray());
    }

    @Test
    public void testNoDuplicateTitles() {
        item1 = ToDoItem.of("Title", "Description1");
        item2 = ToDoItem.of("Title", "Description1");
        toDoList.addItemToList(item1);
        Assertions.assertThrows(DuplicateItemException.class, () -> toDoList.addItemToList(item2));
    }

    @Test
    public void testSeveralItemsToggling() {
        item1 = ToDoItem.of("Title1", "Description1");
        item2 = ToDoItem.of("Title2", "Description2");
        toDoList.addItemToList(item1);
        toDoList.addItemToList(item2);
        toDoList.multipleToggle();
        Assertions.assertEquals(item1.getStatus(), item2.getStatus());
    }

    @Test
    public void testSeveralItemsCompletion() {
        item1 = ToDoItem.of("Title1", "Description1");
        item2 = ToDoItem.of("Title2", "Description2");
        toDoList.addItemToList(item1);
        toDoList.addItemToList(item2);
        toDoList.multipleComplete();
        Assertions.assertEquals(item1.getStatus(), item2.getStatus());
    }
}
