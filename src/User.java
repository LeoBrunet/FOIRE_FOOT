import java.io.File;

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

    boolean comparePassword(String password){
        return password.equals(this.password);
    }

    public String getPassword() {
        return password;
    }
}
