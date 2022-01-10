import com.foirfoot.dao.UserDAOMySQL;
import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import exceptions.ClubNotFoundException;
import exceptions.ProductNotFoundException;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class TestUser {
    private final static Facade facade = new Facade();

    @Test
    public void testLoginOK() {
        try {
            User user = facade.login("leobrunet91@gmail.com", "1234");
            assertEquals(user.getEmail(), "leobrunet91@gmail.com");
            assertEquals(user.getPassword(), DigestUtils.sha1Hex("1234"));
        } catch (UserNotFoundException | WrongPasswordException | ClubNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> facade.login("leobrunet92@gmail.com", "1234"));
    }

    @Test
    public void testLoginWrongPassword() {
        assertThrows(WrongPasswordException.class, () -> facade.login("leobrunet91@gmail.com", "123456"));
    }

    @Test
    public void testGetAllUsers() {
        List<Optional<User>> users = new UserDAOMySQL().getAll();
        assertEquals(1, users.size());
    }

    @Test(expected = SQLIntegrityConstraintViolationException.class)
    public void createUserAlreadyExist() throws SQLIntegrityConstraintViolationException {
        UserDAOMySQL user = new UserDAOMySQL();
        user.save(new User(null, null, "admin", DigestUtils.sha1Hex("admin"), RoleName.classic, -1, -1, false,new Basket()));
    }
}
