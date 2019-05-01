package storage;

import java.util.ArrayList;
import java.util.HashMap;

public interface Dao<T> {

    boolean update(T obj);

    boolean update(T obj, HashMap<String, String> params);

    boolean save(T obj);

    boolean delete(String uniqueId);

    ArrayList<T> find(HashMap<String, String> params);
}
