import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOMySQL implements DAO{

    private List<User> users = new ArrayList<>();

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    public Optional<User> getUserByEmail(String email){
        User user = null; // add value from database
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }
}
