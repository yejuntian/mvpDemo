package service_not_kill.example.tianyejun.rxjava.laoluo.rxjava3;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;


public class DownLoaderUtils {
    private OkHttpClient okHttpClient;

    public DownLoaderUtils() {
        okHttpClient = new OkHttpClient();
    }

    /**
     * 声明一个观察者，作为返回结果
     * @param path
     * @return
     */
    public Observable<byte[]> downLoaderImage(final String path) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    //访问网络
                    Request request = new Request.Builder().url(path).build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] data = response.body().bytes();
                                if (data != null) {
                                    subscriber.onNext(data);
                                }
                                subscriber.onCompleted();
                            }

                        }
                    });
                }

            }
        });
    }
}
