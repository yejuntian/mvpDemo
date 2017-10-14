package service_not_kill.example.tianyejun.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


public class OperatorActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
    }

    public void clickMap(View view) {
        //map转换把一个对象转换成另外一个对象
        Observable.just(123)
                //Integer表示转换前的数据类型，String表示转换后的数据类型
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer integer) {
                        return integer + "";
                    }
                })
                //String表示转换前的数据类型，Long表示转换后的数据类型
                .map(new Func1<String, Long>() {
                    @Override
                    public Long call(String s) {
                        return Long.parseLong(s);
                    }
                })
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("转换后的 aLong=" + aLong);
                    }
                });
                /*.subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("转换后的 s=" + s);
                    }
                });*/
    }
}
