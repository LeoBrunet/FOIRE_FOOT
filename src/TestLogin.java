import com.foirfoot.dao.UserDAOMySQL;
import com.foirfoot.model.Facade;
import com.foirfoot.model.User;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestLogin {
    private final static Facade facade = new Facade();

    @Test
    public void testLoginOK() {
        try {
            User user = facade.login("leobrunet91@gmail.com", "1234");
            assertEquals(user.getEmail(), "leobrunet91@gmail.com");
            assertEquals(user.getPassword(), DigestUtils.sha1Hex("1234"));
        } catch (UserNotFoundException | WrongPasswordException e) {
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
        List<User> users = new UserDAOMySQL().getAll();
        assertEquals(1, users.size());
    }
}
