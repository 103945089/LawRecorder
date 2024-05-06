package com.zhgs.lawrecorder.http


import com.zhgs.lawrecorder.http.interceptors.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit


object RetrofitManager {
    var mApi: NetworkApi? = null
    private const val CONNECTION_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L

    var API_URL = "https://www.wanandroid.com/"

    fun getApiService(): NetworkApi {
        if (null == mApi) {
            synchronized(this) {
                if (null == mApi) {
                    return buildRetrofit(API_URL, buildClient()).create(NetworkApi::class.java)
                }
            }
        }
        return mApi!!
    }


    private fun buildClient(): OkHttpClient.Builder {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(HeaderInterceptor())
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .proxy(Proxy.NO_PROXY)
    }

    private fun buildRetrofit(url: String, builder: OkHttpClient.Builder): Retrofit {
        val client = builder.build()
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(client).build()
    }

}