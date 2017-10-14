package service_not_kill.example.tianyejun.rxjava.laoluo.rxjava;

/**
 * 被观察者
 */

public interface Watched {
    public void addWatcher(Watcher watcher);
    public void removeWatcher(Watcher watcher);
    public void notifyAllWatchers(String s);
}
