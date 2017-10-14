package service_not_kill.example.tianyejun.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;


/**
 * observable（被观察者）和subscriber（订阅者）可以做任何事情
 * observable可以是一个网络请求，subscriber来显示请求结果
 * observable可以是一个数据库查询，subscriber来显示查询结果
 * observable可以是按钮点击事件，subscriber来响应点击事件
 * observable可以是大图片文件的加载解析，subscriber来展示解析后的图片
 * <p>
 * 常用的操作符（options）
 * map：转换对象
 * flatMap：平铺对象
 * filter：过滤
 * distinct：去重复（独特的）
 * take：从开始取出固定个数
 * doOnnext：输出元素之间的额外操作
 * toList 打包对象为集合
 * <p>
 * 常用的操作符（options）
 * map：转换对象
 * flatMap：平铺对象
 * filter：过滤
 * distinct：去重复（独特的）
 * take：从开始取出固定个数
 * doOnnext：输出元素之间的额外操作
 * toList 打包对象为集合
 */


/**
 * 常用的操作符（options）
 * map：转换对象
 * flatMap：平铺对象
 * filter：过滤
 * distinct：去重复（独特的）
 * take：从开始取出固定个数
 * doOnnext：输出元素之间的额外操作
 * toList 打包对象为集合
 */

/**
 * Scheduler 调度器，用于线程控制
 * Schedulers.immediate()
 * Schedulers.newThread()
 * Schedulers.io()
 * Schedules.computation()
 * AndroidSchedulers.mainThread()
 */
public class CommonActivity extends Activity {
    Subscriber<String> subsriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 基本实现
     *
     * @param view
     */
    public void click(View view) {
//        相当于工厂流水线

        //创建observable       被观察者/可观察对象
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //onNext可以别多次调用
                subscriber.onNext("点击事件");
                //事件序列结束标记
                subscriber.onCompleted();
                //错误异常 （这里onCompleted()和onError()方法只能有一个被回调）
//                subscriber.onError();

            }
        }).subscribe(new Observer<String>() {//订阅事件  指定观察者，被观察这必须指定观察者，整个事件流程才可以被执行

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

        });
    }

    /**
     * 被观察者的变形
     * @param view
     */
    public void click1(View view) {
        Observable.just(1, 2).subscribe(new Observer<Integer>() {//Observer其实就是一个观察者
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    public void click2(View view) {
        String[] array = new String[]{"url1", "url2"};
        subsriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                System.out.println("事件执行前被调用onStart");
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        Observable.from(array).subscribe(subsriber);
    }

    /**
     * 变换3
     * @param view
     */
    public void onClick3(View view) {
        String[] array = new String[]{"image1", "image2"};
        Observable.from(array).subscribe(new Action1<String>() {//相当于onNext（）
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {//相当于onError（）
                System.out.println("相当于Error方法");
            }
        }, new Action0() {
            @Override
            public void call() {//相当于onComplete（）
                System.out.println("相当于complete方法");
            }
        });
    }

    /**
     * 变换4
     * @param view
     */
    public void onClick4(View view) {
        String[] array = new String[]{"image1", "image2"};
        Observable.from(array).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        if (subsriber != null && !subsriber.isUnsubscribed()) {
            subsriber.unsubscribe();
            System.out.println("事件取消订阅被执行unsubscribe");
        }
    }
}

