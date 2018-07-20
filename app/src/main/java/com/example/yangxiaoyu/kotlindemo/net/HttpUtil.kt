package com.example.yangxiaoyu.kotlindemo.net

import android.content.Context
import android.util.Log
import com.example.yangxiaoyu.kotlindemo.entity.VideoBean
import com.example.yangxiaoyu.kotlindemo.net.RxHelper.Companion.handleResult
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
/**
 * Created by yangxy on 2018/7/20.
 */
class HttpUtil  {
    /**
     * 在访问HttpMethods时创建单例
     */
     object SingletonHolder {
         val INSTANCE = HttpUtil()
    }


    /**
     * 添加线程管理并订阅
     * @param ob
     * @param subscriber
     * @param event Activity 生命周期
     * @param lifecycleSubject
     */
    fun toSubscribe(ob: Observable, event: ActivityLifeCycleEvent,
                    lifecycleSubject: PublishSubject<ActivityLifeCycleEvent>,
                    subscriber: DataCallback<*>) {
        //数据预处理
        val result =RxHelper.handleResult(event, lifecycleSubject)
        val observable = ob.compose(result)
        //不需要缓存
        observable.subscribe(subscriber)
        //缓存
        //        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh).subscribe(subscriber);
    }


    fun doGet(lifecycleSubject: PublishSubject<ActivityLifeCycleEvent>, context: Context) {
        val ob = NetApi().getDefault().getAppConfig("127", "sbys")
        val ob2 = NetApi().getDefault().getVideo("0", "1")

        HttpUtil.instance.toSubscribe(ob2, ActivityLifeCycleEvent.DESTROY, lifecycleSubject, object : AppConfigCallBack() {
            override fun onErrors(e: Throwable) {
                e.printStackTrace()
                Log.e(TAG, e.message)
            }

            override fun onResponse(response: VideoBean) {
                Log.e(TAG, response.getData()?.get(0).getTitle())
            }
        })
    }

    companion object {

        private val TAG = "HttpUtil"
        /**
         * 获取单例
         */
        val instance: HttpUtil
            get() = SingletonHolder.INSTANCE
    }
}