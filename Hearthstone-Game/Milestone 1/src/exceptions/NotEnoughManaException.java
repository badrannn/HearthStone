package exceptions;

public class NotEnoughManaException extends HearthstoneException {
    public NotEnoughManaException() {
    }

    public NotEnoughManaException(String message) {
        super(message);
    }
}
