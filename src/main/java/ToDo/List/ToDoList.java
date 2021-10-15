package ToDo.List;

import ToDo.Item.ItemStatus;
import ToDo.Item.ToDoItem;
import ToDo.exceptions.DuplicateItemException;
import ToDo.exceptions.TitleListValidationException;
import ToDo.exceptions.ToDoItemValidationException;

import java.util.ArrayList;
import java.util.Comparator;

public class ToDoList {
    private String title;
    private ToDoItem item;
    private ArrayList<ToDoItem> toDoItemArrayList;
    private ArrayList<ToDoItem> filteredList;

    public ToDoList(String title) {
        this.title = title;
        verifyListTitle(title);
        this.toDoItemArrayList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
    }

    public static ToDoList of(String title) {
        if (title == null || title.isBlank()) {
            throw new ToDoItemValidationException("Null or Blank title");
        } else {
            return new ToDoList(title);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ToDoItem> getToDoItemArrayList() {
        return toDoItemArrayList;
    }

    public ArrayList<ToDoItem> getFilteredList() {
        return filteredList;
    }

    public void verifyListTitle(String title) {
        if (title.isBlank()) {
            throw new TitleListValidationException("You cannot create list with blank title");
        }
    }

    public boolean addItemToList(ToDoItem item) {
        for (ToDoItem toDoItem : toDoItemArrayList) {
            if (toDoItem.getTitle().equals(item.getTitle())) {
                throw new DuplicateItemException("There is an item with the same title in the list already");
            }
        }
        if (toDoItemArrayList.contains(item)) return false;
        else {
            return toDoItemArrayList.add(item);
        }
    }

    public void combineListsIntoOne(String newTitle, ToDoList firstList, ToDoList secondList) {
        if (firstList.getToDoItemArrayList() != null && secondList.getToDoItemArrayList() != null) {
            firstList.getToDoItemArrayList().addAll(secondList.getToDoItemArrayList());
            firstList.setTitle(newTitle);
        }
    }

    public boolean removeItem(String title) {
        if (findItem(title) == -1) return false;
        else {
            toDoItemArrayList.remove(item);
            return true;
        }
    }

    private int findItem(String itemTitle) {
        for (int i = 0; i < toDoItemArrayList.size(); i++) {
            ToDoItem toDoItem = toDoItemArrayList.get(i);
            if (toDoItem.getTitle().equals(itemTitle)) {
                return i;
            }
        }
        return -1;
    }

    public void filterByStatus(ItemStatus status) {
        for (ToDoItem item : toDoItemArrayList) {
            if (item.getStatus() == status) {
                filteredList.add(item);
            }
        }
    }

    public ArrayList<ToDoItem> sortByTitle() {
        ArrayList<ToDoItem> list = new ArrayList<>(toDoItemArrayList);
        list.sort(Comparator.comparing(ToDoItem::getTitle));
        return list;
    }

    public void multipleToggle() {
        for (ToDoItem toDoItem : toDoItemArrayList) {
            if (toDoItem.getStatus() == ItemStatus.PENDING) {
                toDoItem.toggleStatus();
            } else if (toDoItem.getStatus() == ItemStatus.IN_PROGRESS) {
                toDoItem.toggleStatus();
            }
        }
    }

    public void multipleComplete() {
        for (ToDoItem toDoItem : toDoItemArrayList) {
            if (toDoItem.getStatus() == ItemStatus.IN_PROGRESS) {
                toDoItem.complete();
            }
        }
    }

}
