import java.util.Optional;

public class Facade {

    private final AbstractDAOFactory<?> abstractDAOFactory = new MySQLDAOFactory();

    public User login(String email_address, String password) throws UserNotFoundException, WrongPasswordException {
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        Optional<User> optionalUser = userDAOMySQL.getUserByEmail(email_address);
        User userFoundInDatabase = optionalUser.orElseThrow(() -> new UserNotFoundException("User not found in database."));
        return userFoundInDatabase.login(password);
    }
}
