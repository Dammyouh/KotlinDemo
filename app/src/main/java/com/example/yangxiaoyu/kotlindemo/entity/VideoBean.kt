package com.example.yangxiaoyu.kotlindemo.entity

import com.example.yangxiaoyu.kotlindemo.base.PoKo
import com.google.gson.annotations.SerializedName

/**
 * Created by yangxy on 2018/7/23.
 */

@PoKo
data class VideoBean(var i : Int = 0) {
    @SerializedName("code")
    var code : Int = 0

    @SerializedName("message")
    var message : String? = null

    @SerializedName("data")
    var data : List<Video>? = null


    class Video{
        var title : String ? = null
        var cover : String ? = null
    }

}