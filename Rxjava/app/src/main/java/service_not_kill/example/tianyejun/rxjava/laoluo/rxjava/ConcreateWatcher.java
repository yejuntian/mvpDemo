package service_not_kill.example.tianyejun.rxjava.laoluo.rxjava;


import java.util.ArrayList;
import java.util.List;

public class ConcreateWatcher implements Watched{
    private List<Watcher> list = new ArrayList<>();
    @Override
    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    @Override
    public void notifyAllWatchers(String s) {
        for (int i = 0; i < list.size(); i++) {
           list.get(i).update();
        }
    }
}
