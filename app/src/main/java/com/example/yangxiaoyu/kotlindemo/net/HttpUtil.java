package com.example.yangxiaoyu.kotlindemo.net;

import android.content.Context;
import android.util.Log;

import com.example.yangxiaoyu.kotlindemo.entity.VideoBean;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by yangxy on 2018/7/23.
 */
public class HttpUtil {

    private static  final  String TAG ="HttpUtil";

    private HttpUtil(){}
    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }
    /**
     * 获取单例
     */
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 添加线程管理并订阅
     * @param ob
     * @param subscriber
     * @param event Activity 生命周期
     * @param lifecycleSubject
     */
    public void toSubscribe(Observable<String> ob, final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject, final DataCallback<VideoBean> subscriber) {
        //数据预处理
        ObservableTransformer<String, Object> result = RxHelper.handleResult(event, lifecycleSubject);
        Observable observable = ob.compose(result);
        //不需要缓存
        observable.subscribe(subscriber);
        //缓存
//        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh).subscribe(subscriber);
    }


    public void doGet(PublishSubject<ActivityLifeCycleEvent> lifecycleSubject, Context context){
        Observable<String> ob = new NetApi().getDefault().getAppConfig("127", "sbys");
        Observable<String> ob2 = new NetApi().getDefault().getVideo("0","1");

        HttpUtil.getInstance().toSubscribe(ob2, ActivityLifeCycleEvent.DESTROY, lifecycleSubject, new AppConfigCallBack() {

            @Override
            public void onErrors(Throwable e) {
                e.printStackTrace();
                Log.e(TAG,e.getMessage());
            }

            @Override
            public void onResponse(VideoBean response) {
//                Log.e(TAG,response.getData().get(1));
                VideoBean bean = new VideoBean();
                Log.d(TAG, "onResponse: "+ response.getCode());
                Log.d(TAG, "onResponse: "+ response.getMessage());
                Log.d(TAG, "onResponse: "+ response.getData());
                Log.d(TAG, "onResponse: "+ response.getData().get(0).getCover());
                Log.d(TAG, "onResponse: "+ response.getData().get(0).getTitle());
            }
        });
    }
}
