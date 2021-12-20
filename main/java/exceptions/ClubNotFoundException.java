package exceptions;

public class ClubNotFoundException extends Exception{
    public ClubNotFoundException() {
        super("Club not found");
    }
}
