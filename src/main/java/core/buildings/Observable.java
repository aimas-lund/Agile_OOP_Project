package core.buildings;

public interface Observable {
    void addChangeListener(Observer newListener);

    void removeChangeListener(Observer listener);

    void notifyListeners(Object oldValue, Object newValue);
}
