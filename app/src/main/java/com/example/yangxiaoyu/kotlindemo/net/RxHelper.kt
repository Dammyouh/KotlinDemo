package com.example.yangxiaoyu.kotlindemo.net

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject
import io.reactivex.ObservableSource
import io.reactivex.functions.Predicate



/**
 * Created by yangxy on 2018/7/20.
 */
class RxHelper {

    /**
     * 利用Observable.takeUntil()停止网络请求
     *
     * @param event
     * @param lifecycleSubject
     * @param <T>
     * @return
    </T> */
    fun <T> bindUntilEvent(event: ActivityLifeCycleEvent,
                           lifecycleSubject: PublishSubject<ActivityLifeCycleEvent>): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            val compareLifecycleObservable = lifecycleSubject.filter {
                activityLifeCycleEvent -> activityLifeCycleEvent == event
            }
            observable.takeUntil(compareLifecycleObservable)
        }
    }

    companion object {
        fun <T> handleResult( event: ActivityLifeCycleEvent,
                             lifecycleSubject: PublishSubject<ActivityLifeCycleEvent>): ObservableTransformer<String, T> {
            return ObservableTransformer<String, T> {
                @Override
                fun apply(observable: Observable<String>): ObservableSource<T> {

                    var compareLifecycleObservable: Observable<ActivityLifeCycleEvent> =

                            lifecycleSubject.filter(Predicate<ActivityLifeCycleEvent> {
                                @Override
                                fun test(activityLifeCycleEvent: ActivityLifeCycleEvent): Boolean {
                                    return activityLifeCycleEvent == event
                                }
                                return @Predicate
                            })
                }
                    return observable.takeUntil(compareLifecycleObservable).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()) as ObservableSource<T>;
            }
        }
    }


//    /**
//     * @param <T>
//     * @return
//    </T> */
//    companion object {
//        fun <T> handleResult(event: ActivityLifeCycleEvent, lifecycleSubject: PublishSubject<ActivityLifeCycleEvent>): ObservableTransformer<String, T> {
//            return ObservableTransformer {
//                observable ->
//                val compareLifecycleObservable = lifecycleSubject.filter {
//                    activityLifeCycleEvent -> activityLifeCycleEvent == event
//                }
//                observable.takeUntil(compareLifecycleObservable)
//                        .subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .observeOn(AndroidSchedulers.mainThread()) as ObservableSource<T>
//            }
//        }
//
//    }







        /**
         * 创建成功的数据
         *
         * @param data
         * @param <T>
         * @return
        </T> */
        private fun <T> createData(data: T): Observable<T> {
            return Observable.create({ observableEmitter ->
                try {
                    observableEmitter.onNext(data)
                    observableEmitter.onComplete()
                } catch (e: Exception) {
                    observableEmitter.onError(e)
                }
            })

        }


}