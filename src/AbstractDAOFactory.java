public abstract class AbstractDAOFactory {

    public UserDAOMySQL createUserDAOMYSQL(){
        return new UserDAOMySQL();
    };
}
