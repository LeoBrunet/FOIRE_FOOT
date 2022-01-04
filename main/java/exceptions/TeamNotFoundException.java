package exceptions;

import java.util.function.Supplier;

public class TeamNotFoundException extends Exception {
    public TeamNotFoundException() {
        super("Team not found");
    }
}
