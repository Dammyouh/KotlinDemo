package com.example.yangxiaoyu.kotlindemo.net

import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

/**
 * Created by yangxy on 2018/7/20.
 */
interface ApiService {
    @GET("/NewsControl/recommendNews/randomBySection")
    fun getVideo(@Query("secId") secId: String, @Query("newsId") newsId: String): Observable

    @GET("/App_Config/appConfig/getAppCinfig")
    fun getAppConfig(@Query("siteId") siteId: String,@Query("tagName") tagName:String) : Observable
}