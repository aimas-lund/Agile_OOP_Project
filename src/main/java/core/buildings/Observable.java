package core.buildings;

public interface Observable {
    void addChangeListener(Observer newListener);

    void removeChangeListener(Observer listener);

    void notifyListeners(Object source, Event action, Object oldValue, Object newValue);
}
