package com.example.jinux.mydemo.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.MenuDialogHelper;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import com.example.jinux.mydemo.R;
import com.google.api.client.json.gson.GsonFactory;
import com.jakewharton.rxbinding.view.RxView;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Action9;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by baidu on 16/3/2.
 */
public class TestRxJava extends Activity {
    private static final String TAG = "TextRxJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_rxjava);
        Observer observer = new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        };

        Subscription subscription = new Subscription() {
            @Override
            public void unsubscribe() {

            }

            @Override
            public boolean isUnsubscribed() {
                return false;
            }
        };

        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");

            }

            @Override
            public void onNext(String msg) {
                Log.d(TAG, "onNext = " + msg);
            }
        };

        Observable observable;
//        observable = Observable.create(new Observable.OnSubscribe<String>() {
//
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("hello");
//                subscriber.onCompleted();
//            }
//        });
//        observable.subscribe(subscriber);

        observable = Observable.just("hello", "world");
        observable.subscribe(subscriber);

        observable = Observable.from(new String[]{"wowo", "caca"});
        observable.subscribe(subscriber);

        Action1 action = new Action1() {
            @Override
            public void call(Object o) {
                Log.e(TAG, "haha - " + o);
            }
        };
        observable.subscribe(action);

//        String[] names = {"John", "Tom", "Linus"};
//        Observable.from(names).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                showToast("Hello " + s);
//            }
//        });

//        Observable.just(1, 2, 3, 4)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        showToast("number: " + integer);
//                    }
//                });

        Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
        Observable<String> observable2 = observable1.lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d("JIN", "on complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("JIN", "on error");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        subscriber.onNext("" + integer);
                    }
                };
            }
        });

        observable2.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("JIN", "new action");
            }
        });

        RxView.clicks(findViewById(R.id.testRxBinding)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                showToast("hello world");
            }
        });

        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestRetrofit test = retrofit.create(TestRetrofit.class);
        test.showProject("square", "retrofit").enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Response<List<Contributor>> response, Retrofit retrofit) {

                for (Contributor contributor : response.body()) {
                    Log.d("JIN", "the contributor = " + contributor.login);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }

    interface TestRetrofit {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> showProject(@Path("owner") String owner,
                                            @Path("repo") String repo);
    }

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
