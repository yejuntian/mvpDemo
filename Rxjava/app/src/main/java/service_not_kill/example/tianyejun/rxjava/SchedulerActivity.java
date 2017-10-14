package service_not_kill.example.tianyejun.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class SchedulerActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
    }

    public void clickScheduler(View view) {
        //创建被观察者observable  被观察者/可观察对象
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                //在子线程中处理
                System.out.println("当前执行线程的名字：" + Thread.currentThread().getName());
                subscriber.onNext("执行onNext方法");
                subscriber.onCompleted();

            }
        })
                .subscribeOn(Schedulers.io())//让我们subscriber操作在异步线程去执行
                .observeOn(AndroidSchedulers.mainThread())//让订阅者代码执行在UI主线程
                .subscribe(new Observer<Object>() {//订阅事件指定观察者 ，被观察者必须指定了观察者，事件流程才能够执行
                    @Override
                    public void onNext(Object o) {
                        System.out.println(o.toString());
                        System.out.println("onNext()执行当前线程的名字：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }


                });
    }

    public void clickScheduler1(View view) {
        Observable.just("123")
                .subscribeOn(Schedulers.immediate())
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        System.out.println("call1 "+Thread.currentThread().getName());
                        return Integer.parseInt(s);
                    }
                })
                .map(new Func1<Integer, Long>() {
                    @Override
                    public Long call(Integer integer) {
                        System.out.println("call2 "+Thread.currentThread().getName());
                        return Long.parseLong(integer+"");
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onNext(Long s) {

                        System.out.println(s);
                        System.out.println("onNext 当前线程 "+Thread.currentThread().getName());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }
}
