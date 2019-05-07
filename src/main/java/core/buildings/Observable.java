package core.buildings;

public interface Observable {
    void addChangeListener(Observer newListener);

    void removeChangeListener(Observer listener);

    void notifyListeners(Object source, Event event, Object oldValue, Object newValue);
}
