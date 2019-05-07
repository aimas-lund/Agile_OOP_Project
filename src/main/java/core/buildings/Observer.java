package core.buildings;

public interface Observer {
    void objectChanged(Object source, Event action, Object oldValue, Object newValue);
}
