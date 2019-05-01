package storage;

import management.Department;

public interface IDelete<T> {
    boolean delete(T obj, Department department);
}
