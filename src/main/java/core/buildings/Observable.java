package core.buildings;

public interface Observable {
    void addChangeListener(Observer newListener);

    void removeChangeListener(Observer listener);

    void notifyListeners(Object source, String action, Object oldValue, Object newValue);
}
