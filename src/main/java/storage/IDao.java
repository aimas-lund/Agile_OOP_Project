package storage;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDao<T> {

    boolean save(T obj);

    ArrayList<T> find(HashMap<String, String> params);

    boolean delete(String uniqueId);

    boolean update(T obj);

}
