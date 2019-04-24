package management;

public interface Observable {

    public void register(Observer newObserver);

    public void unregister(Observer deleteObserver);

    public void notifyObservers();

}
