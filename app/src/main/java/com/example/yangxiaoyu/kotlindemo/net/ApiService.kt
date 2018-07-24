package com.example.yangxiaoyu.kotlindemo.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by yangxy on 2018/7/20.
 */
interface ApiService {
    @GET("/NewsControl/recommendNews/randomBySection")
    fun getVideo(@Query("secId") secId: String, @Query("newsId") newsId: String): Observable<String>

    @GET("/App_Config/appConfig/getAppCinfig")
    fun getAppConfig(@Query("siteId") siteId: String,@Query("tagName") tagName:String) : Observable<String>
}