public interface AbstractDAOFactory<T> {
    public T create(String mySQLDAOType);
}
