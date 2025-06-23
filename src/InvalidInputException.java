public class InvalidInputException extends RuntimeException {
    public InvalidInputException(){
        super("Cannot add null");
    }
public InvalidInputException(String message) {
    super(message);
}
}
