package core.buildings;

public interface Observer {
    void objectChanged(Object oldValue, Object newValue);
}
