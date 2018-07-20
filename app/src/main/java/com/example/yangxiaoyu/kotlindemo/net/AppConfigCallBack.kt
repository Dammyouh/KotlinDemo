package com.example.yangxiaoyu.kotlindemo.net

import android.util.Log
import com.example.yangxiaoyu.kotlindemo.entity.VideoBean
import com.google.gson.Gson



/**
 * Created by yangxy on 2018/7/20.
 */
abstract class AppConfigCallBack : DataCallback<VideoBean>() {

    @Throws(Exception::class)
    override fun parseNetworkResponse(response: String): VideoBean {
        Log.e("Parse String", "Parse String  *****$response")
        val gson = Gson()
        return gson.fromJson(response, VideoBean::class.java)
    }


}