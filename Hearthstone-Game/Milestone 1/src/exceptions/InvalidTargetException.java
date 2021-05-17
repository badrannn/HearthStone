package exceptions;

public class InvalidTargetException extends HearthstoneException {
    public InvalidTargetException() {
    }

    public InvalidTargetException(String message) {
        super(message);
    }
}
