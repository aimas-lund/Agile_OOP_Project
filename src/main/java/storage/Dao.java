package storage;

public interface Dao<T> {

    public void update(T obj);
    public void update(T obj, String[] params);

    public void save(T obj);

    public void delete(T obj);

    public T find(T obj);
}
