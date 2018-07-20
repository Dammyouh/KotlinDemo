package com.example.yangxiaoyu.kotlindemo.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco



/**
 * Created by yangxy on 2018/6/7.
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initFrsco()

    }


    private fun initFrsco() {
        Fresco.initialize(this)
    }

}