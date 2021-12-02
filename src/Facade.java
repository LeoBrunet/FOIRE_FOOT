import java.util.Optional;
import java.util.function.Supplier;

public class Facade {

    private AbstractDAOFactory abstractDAOFactory;
    private User user;

    public void login(String email_address, String password) throws UserNotFoundException {
        UserDAOMySQL userDAOMySQL = this.abstractDAOFactory.createUserDAOMYSQL();
        Optional<User> optionalUser = userDAOMySQL.getUserByEmail(email_address);
        optionalUser.orElseThrow(() -> new UserNotFoundException("User not found in database."));
        optionalUser.ifPresent(user -> {
            this.user.comparePassword(user.getPassword());
        });
    }
}
