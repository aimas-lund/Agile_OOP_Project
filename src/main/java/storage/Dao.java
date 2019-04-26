package storage;

import java.util.ArrayList;
import java.util.HashMap;

public interface Dao<T> {

    void update(T obj);

    void update(T obj, String[] params);

    void save(T obj);

    void delete(T obj);

    T find(T obj);

    ArrayList<T> find(HashMap<String, String> params);
}
