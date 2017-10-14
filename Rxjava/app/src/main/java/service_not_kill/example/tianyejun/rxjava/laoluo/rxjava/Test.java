package service_not_kill.example.tianyejun.rxjava.laoluo.rxjava;

/**
 * Created by tianyejun on 2017/9/9.
 */

public class Test {
    public static void main(String[] args) {
        Watched xiaoming = new ConcreateWatcher();

        Watcher watcher =new ConCreaterWatcher();
        Watcher watcher1 =new ConCreaterWatcher();
        Watcher watcher2 =new ConCreaterWatcher();

        xiaoming.addWatcher(watcher);
        xiaoming.addWatcher(watcher1);
        xiaoming.addWatcher(watcher2);

        xiaoming.notifyAllWatchers("");
    }
}
