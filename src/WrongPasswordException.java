public class WrongPasswordException extends Exception{
    public WrongPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
