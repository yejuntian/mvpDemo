package service_not_kill.example.tianyejun.rxjava.laoluo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import service_not_kill.example.tianyejun.rxjava.R;


public class MyActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    /**
     * create方法第一种写法方式
     * @param view
     */
    public void clickCreate(View view){
        RxUtils.createObservable();
    }

    /**
     * 测试create方法第二种写法形式
     * @param view
     */
    public void clickCreate2(View view){
        RxUtils.createObservable2();
    }

    /**
     * 测试from方法
     * @param view
     */
    public void from(View view){
        RxUtils.from();
    }
    /**
     * 测试interval方法
     * @param view
     */
    public void interval(View view){
        RxUtils.interval();
    }

    /**
     * 测试arrays方法
     * @param view
     */
    public void just(View view){
        RxUtils.just();
    }

    /**
     * 测试arrays方法
     * @param view
     */
    public void range(View view){
        RxUtils.range();
    }
    /**
     * 测试filter方法
     * @param view
     */
    public void filter(View view){
        RxUtils.filter();
    }
}
