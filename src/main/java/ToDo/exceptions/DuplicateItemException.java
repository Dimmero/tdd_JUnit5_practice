package ToDo.exceptions;

public class DuplicateItemException extends RuntimeException{
    public DuplicateItemException(String s) {
        super(s);
    }
}
