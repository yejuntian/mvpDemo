package service_not_kill.example.tianyejun.rxjava.laoluo.activity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class RxUtils {
    private static final String TAG = "RxUtils";

    public static void createObservable() {
        //创建被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //没有别其他订阅
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onStart();
                    subscriber.onNext("hello");
                    subscriber.onNext("world");
                    subscriber.onNext(downLoadJson());
                    subscriber.onCompleted();
                }
            }
        });

        //创建订阅
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
                System.out.println("最先被执行。。。。。");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

        };
        //被观察者被订阅
        observable.subscribe(subscriber);

    }

    public static String downLoadJson() {
        return "downLoadJson";
    }

    public static void createObservable2() {
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 10; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }

            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext  integer =" + integer);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }


        });
    }

    /**
     * from方法
     */
    public static void from() {
        Integer[] arr = {1, 2, 3, 4};
        Observable.from(arr).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("from方法遍历 integer = " + integer);
            }
        });
    }

    /**
     * inteval方法
     */
    public static void interval() {
        Integer[] arr = {1, 2, 3, 4};
        Observable.interval(1, 1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(aLong);
            }
        });
    }

    /**
     * just处理集合
     */
    public static void just() {
        String str1[] = new String[]{"123", "234", "345"};
        String str2[] = new String[]{"a", "b", "c"};
        Observable.just(str1, str2).subscribe(new Subscriber<String[]>() {
            @Override
            public void onStart() {
                super.onStart();
                System.out.println("start调用");
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String[] strings) {
                for (int i = 0; i < strings.length; i++) {
                    System.out.println("数组遍历  str=" + strings[i]);
                }
            }
        });
    }

    /**
     * 取范围
     */
    public static void range() {
        Observable.range(1, 10).subscribe(new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
                System.out.println("onStart");
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("range 方法范围 integer =" + integer);
            }
        });
    }

    /**
     * 测试过滤条件
     */
    public static void filter() {
        Observable.just(1, 2, 3, 4, 4, 5, 7, 8, 9)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer<=5;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("integer = " + integer);
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                });
    }
}
