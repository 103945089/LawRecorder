package com.zhgs.lawrecorder.http.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val request = oldRequest.newBuilder()
            .addHeader("ApiVersion", "v18")
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("Accept-Encoding", "gzip, deflate")
            .addHeader("Connection", "keep-alive")
            .build()
        return chain.proceed(request)
    }
}