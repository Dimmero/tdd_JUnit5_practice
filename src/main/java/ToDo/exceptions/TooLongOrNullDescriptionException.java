package ToDo.exceptions;

public class TooLongOrNullDescriptionException extends RuntimeException {
    public TooLongOrNullDescriptionException(String s) {
        super(s);
    }
}
