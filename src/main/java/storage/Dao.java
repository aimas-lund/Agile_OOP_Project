package storage;

import java.util.ArrayList;
import java.util.HashMap;

public interface Dao<T> {

    boolean save(T obj);

    ArrayList<T> find(HashMap<String, String> params);

    boolean delete(String uniqueId);

    boolean update(T obj);

    boolean update(T obj, HashMap<String, String> params);
}
