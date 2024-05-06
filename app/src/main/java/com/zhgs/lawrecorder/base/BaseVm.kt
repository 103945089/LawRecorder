package com.zhgs.lawrecorder.base

import com.zhgs.baseproject.base.BaseViewModel
import com.zhgs.lawrecorder.http.NetworkApi
import com.zhgs.lawrecorder.http.Response
import com.zhgs.lawrecorder.http.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion

abstract class BaseVm: BaseViewModel() {
    val api by lazy { RetrofitManager.getApiService() }

    suspend fun <T> requestFow(
        showLoading: Boolean = true,
        request: suspend NetworkApi.() -> Response<T>?
    ): Flow<Response<T>> {
        if (showLoading) {
            statusFlow.emit(RequestEnum.ON_REQUEST)
        }
        return flow {
            val response =request(api) ?: throw IllegalArgumentException("数据非法，获取响应数据为空")
            if (response.code != 200) {
                throw Throwable("错误错误错误")
            }
            emit(response)
        }.catch {
            statusFlow.emit(RequestEnum.ON_ERR)
            apiErrorFlow.emit(it)
            it.printStackTrace()
        }
            .flowOn(Dispatchers.IO)
            .onCompletion {
                statusFlow.emit( RequestEnum.ON_RESPONSE)
            }
    }
}