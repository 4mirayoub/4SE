public class EmptyQueueException extends RuntimeException {
  public EmptyQueueException(){
    super("Queue is empty");
  }
    public EmptyQueueException(String message) {
        super(message);
    }
}
