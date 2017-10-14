package service_not_kill.example.tianyejun.rxjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import rx.Subscriber;
import service_not_kill.example.tianyejun.rxjava.laoluo.activity.MyActivity;
import service_not_kill.example.tianyejun.rxjava.laoluo.rxjava3.LoadImageActivity;

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
 *
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
 * take：从集合中取出固定个数
 * doOnnext：输出元素之间的额外操作
 * toList 打包对象为集合
 */

/**
 * Scheduler 调度器，用于线程控制
 * Schedulers.immediate() ：默认线程
 * Schedulers.newThread() ：每次都创建新的线程执行任务（一般不使用）
 * Schedulers.io()        ：包含线程池的机制，线程个数无限，可以复用空闲线程
 * Schedules.computation()：cup密集计算的线程，线程数和cpu线程数一样，处理图形运算
 * AndroidSchedulers.mainThread()：android更新界面的UI线程
 *
 * subscribeOn():可执行多次,用来切换操作符的线程
 * observeOn():只需要执行一次，只需要指定订阅者的线程
 */
public class MainActivity extends Activity {
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
        Intent intent = new Intent(MainActivity.this,CommonActivity.class);
        startActivity(intent);

    }
    /**
     * 操作符的使用
     *
     * @param view
     */
    public void click1(View view) {
        Intent intent = new Intent(MainActivity.this,OperatorActivity.class);
        startActivity(intent);

    }
    /**
     * 线程调度器的使用
     *
     * @param view
     */
    public void click2(View view) {
        Intent intent = new Intent(MainActivity.this,SchedulerActivity.class);
        startActivity(intent);
    }
    /**
     * 自学Obera
     *
     */
    public void click3(View view) {
        Intent intent = new Intent(MainActivity.this,MyActivity.class);
        startActivity(intent);
    }
    /**
     * 使用rxjava加载网络图片
     *
     */
    public void click4(View view) {
        Intent intent = new Intent(MainActivity.this,LoadImageActivity.class);
        startActivity(intent);
    }
}
