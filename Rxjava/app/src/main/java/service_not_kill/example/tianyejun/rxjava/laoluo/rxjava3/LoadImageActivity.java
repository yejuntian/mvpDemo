package service_not_kill.example.tianyejun.rxjava.laoluo.rxjava3;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import service_not_kill.example.tianyejun.rxjava.R;

public class LoadImageActivity extends AppCompatActivity {
    private ImageView imageView;
    DownLoaderUtils downLoaderUtils;
    LoginUtils loginUtils;
    private String path = "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=3d2175db3cd3d539d530078052ee8325/b7003af33a87e950c1e1a6491a385343fbf2b425.jpg";
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.iv);
        downLoaderUtils = new DownLoaderUtils();
        loginUtils = new LoginUtils();
         dialog=new ProgressDialog(this);
        dialog.setTitle("正在登录中请稍后。。。。");
    }

    /**
     * 实现下载操作
     * @param view
     */
    public void downLoader(View view) {
        downLoaderUtils.downLoaderImage(path)
                //使用http协议获取数据
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<byte[]>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        System.out.println("主要用户对话框的消失");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        dialog.show();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imageView.setImageBitmap(bitmap);
                    }
                });
    }

    /**
     * 模拟用户登录请求
     * @param view
     */
    public void login(View view){
        String url="";//服务器地址
        Map<String,String> map = new HashMap<>();
        map.put("userName","zhangsan");
        map.put("password","zhangsan");
        loginUtils.login(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("登录请求完毕");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("登录请求获取的结果 s="+s);
                    }
                });
    }
}
