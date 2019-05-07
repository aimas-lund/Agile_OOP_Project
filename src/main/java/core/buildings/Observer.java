package core.buildings;

public interface Observer {
    void objectChanged(Object source, Event event, Object oldValue, Object newValue);
}
