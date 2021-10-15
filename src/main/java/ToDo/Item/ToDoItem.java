package ToDo.Item;

import ToDo.exceptions.ToDoItemValidationException;
import ToDo.exceptions.TooLongOrNullDescriptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ToDoItem implements StatusChangeable {
    private String title;
    private String description;
    private ItemStatus status;
    public static List<Predicate<String>> criteria = new ArrayList<>();
    protected static final Logger logger = LoggerFactory.getLogger(ToDoItem.class);

    public ToDoItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = ItemStatus.PENDING;
    }

    public ToDoItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new ToDoItemValidationException("The title is either null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    public static ToDoItem of(String title, String description) {
        if (criteria.isEmpty()){
        createListOfCriteria();
        }
        if (title == null) {
            throw new ToDoItemValidationException("Null");
        }
        validateTitle(title, criteria);
        validateDescription(description);
        return new ToDoItem(title, description);
    }

    private static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new TooLongOrNullDescriptionException("The description is either null or empty");
        } else if (description.toCharArray().length > 250) {
            throw new TooLongOrNullDescriptionException("The description can't be longer than 250 characters");
        }
    }

    static List<Predicate<String>> createListOfCriteria() {
        Predicate<String> criterionWithNull = Objects::nonNull;
        Predicate<String> criterionWithZeroSize = str -> !str.isBlank();
        criteria.add(criterionWithNull);
        criteria.add(criterionWithZeroSize);
        return criteria;
    }

    private static void validateTitle(String title, List<Predicate<String>> criteria) {
        List<Boolean> booleans;
        booleans = criteria.stream().map(it -> it.test(title)).collect(Collectors.toList());
        booleans = booleans.stream().filter(b -> !b).collect(Collectors.toList());
        if (!booleans.isEmpty()) {
            throw new ToDoItemValidationException("The title is either null or blank");
        }


//        stream pipeline for criteria
//        each criterion get a title to perform checks
//        collect to list of boolean
//        filter to find false, collect to list and check if list size is > 0
    }

    @Override
    public void toggleStatus() {
        if (this.status == ItemStatus.PENDING) {
            this.status = ItemStatus.IN_PROGRESS;
        } else {
            this.status = ItemStatus.PENDING;
        }
    }
    @Override
    public void biDirectionalToggleStatus() {
        if (this.status == ItemStatus.PENDING) {
            this.status = ItemStatus.IN_PROGRESS;
        } else if (this.status == ItemStatus.COMPLETED) {
            this.status = ItemStatus.IN_PROGRESS;
        } else {
            this.status = ItemStatus.PENDING;
        }
    }

    @Override
    public void complete() {
        if (this.status == ItemStatus.IN_PROGRESS) {
            this.status = ItemStatus.COMPLETED;
        }
    }
}
