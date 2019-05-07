package core.buildings;

public interface Observer {
    void objectChanged(Object soruce, String action, Object oldValue, Object newValue);
}
