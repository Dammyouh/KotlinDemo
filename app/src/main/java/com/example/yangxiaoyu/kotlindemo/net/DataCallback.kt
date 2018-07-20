package com.example.yangxiaoyu.kotlindemo.net


import io.reactivex.disposables.Disposable
import io.reactivex.Observer



/**
 * Created by yangxy on 2018/7/20.
 */
abstract class DataCallback<T> : Observer<T> {
    override fun onComplete() {
        onAfter()
    }

    override fun onSubscribe(disposable: Disposable) {
        onBefore(disposable)
    }


    override fun onNext(t: T) {
        try {
            onResponse(parseNetworkResponse(t as String))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onError(e: Throwable) {
        onErrors(e)
    }

    fun onBefore(disposable: Disposable) {

    }
    fun onAfter() {

    }

    abstract fun onErrors(e: Throwable)

    abstract fun onResponse(response: T)

    @Throws(Exception::class)
    abstract fun parseNetworkResponse(response: String): T
}