package com.example.yangxiaoyu.kotlindemo.entity

import com.google.gson.annotations.SerializedName



/**
 * Created by yangxy on 2018/7/20.
 */
class VideoBean {
    @SerializedName("code")
    private var code: Int = 0
    @SerializedName("message")
    private var message: String? = null
    @SerializedName("data")
    private var data: List<Video>? = null


    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getData(): List<Video>? {
        return data
    }

    fun setData(data: List<Video>) {
        this.data = data
    }

    internal inner class Video {

        var title: String? = null
        var cover: String? = null
    }
}