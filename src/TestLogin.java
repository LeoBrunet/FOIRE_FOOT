import org.junit.Test;

import static org.junit.Assert.*;

public class TestLogin {
    private final static Facade facade = new Facade();

    @Test
    public void testLoginOK() {
        try {
            assertEquals(facade.login("leobrunet91@gmail.com", "123456"), new User("leobrunet91@gmail.com", "123456"));
        } catch (UserNotFoundException | WrongPasswordException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> facade.login("leobrunet92@gmail.com", "123456"));
    }

    @Test
    public void testLoginWrongPassword() {
        assertThrows(WrongPasswordException.class, () -> facade.login("leobrunet91@gmail.com", "1234"));
    }
}
