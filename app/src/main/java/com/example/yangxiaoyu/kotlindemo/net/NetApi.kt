package com.example.yangxiaoyu.kotlindemo.net

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by yangxy on 2018/7/20.
 */
class NetApi {
    var SERVICE : ApiService ? = null
    val DEFAULT_TIMEOUT = 10000L


     fun getDefault() : ApiService  {
        if(SERVICE === null){
         val httpClientBuilder : OkHttpClient.Builder = OkHttpClient.Builder()
            httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            httpClientBuilder.addInterceptor{ chain ->
            val request = chain.request()
            val authorizedUrlBuilder = request.url()
                    .newBuilder()
                    .addQueryParameter("key1","value1")
                    .addQueryParameter("key2","value2")

            val newRequest = request.newBuilder()
                    .header("mobileFlag", "adfsaeefe").addHeader("type", "4")
                    .method(request.method(), request.body())
                    .url(authorizedUrlBuilder.build())
                    .build()
            chain.proceed(newRequest)
            }

           SERVICE = Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(NetUrl().BASE_URL)
                    .build().create(ApiService::class.java)

        }
            return  SERVICE!!
    }

    private fun buildGson(): Gson {
        return GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                // 此处可以添加Gson 自定义TypeAdapter
                .create()
    }
}