package Errors;

public class DeadException extends Exception {
    public DeadException() {
        super();
    }

    public DeadException(String message) {
        super(message);
    }
}
