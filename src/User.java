import java.io.File;
import java.util.Objects;

public class User implements Role{
    private String name;
    private String firstname;
    private String email_address;
    private String password;
    private String tel;
    private File picture;

    public User(String email_address, String password) {
        this.email_address = email_address;
        this.password = password;
    }

    public User login(String password) throws WrongPasswordException {
        if(comparePassword(password)) {
            return this;
        } else {
            throw new WrongPasswordException("Password provided mismatch.");
        }
    }

    private boolean comparePassword(String password){
        return password.equals(this.password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email_address, user.email_address) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email_address, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "email_address='" + email_address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
