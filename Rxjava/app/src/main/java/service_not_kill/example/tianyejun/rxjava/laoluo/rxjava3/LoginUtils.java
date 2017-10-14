package service_not_kill.example.tianyejun.rxjava.laoluo.rxjava3;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by tianyejun on 2017/9/9.
 */

public class LoginUtils {
    OkHttpClient okHttpClient;
    public LoginUtils(){
        okHttpClient = new OkHttpClient();
    }

    /**
     * 定义了login操作，使用Rxjava的编程思想
     * @param url
     * @param map
     * @return
     */
    public Observable<String> login(final String url, final Map<String,String > map){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                if(!subscriber.isUnsubscribed()){
                    FormBody.Builder builder=new FormBody.Builder();
                    //添加请求参数到formBody中
                    if(!map.isEmpty()){
                        for(Map.Entry<String,String> entry:map.entrySet()){
                            builder.add(entry.getKey(),entry.getValue());
                        }
                    }
                    RequestBody requestBody=builder.build();
                    //构建post请求
                    Request request=new Request.Builder().url(url).post(requestBody).build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if(response.isSuccessful()){
                                subscriber.onNext(response.body().toString());
                            }
                            subscriber.onCompleted();//访问结束

                        }
                    });
                }

                subscriber.onCompleted();
            }
        });
    }
}
