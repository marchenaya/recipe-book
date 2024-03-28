package com.marchenaya.data.remote.network

import com.marchenaya.data.remote.api.ApiConstants
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


@Reusable
class NetworkInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        try {
            val modifiedUrl = originalRequest.url.newBuilder()
                .addQueryParameter("apiKey", ApiConstants.API_KEY)
                .build()
            val modifiedRequest = originalRequest.newBuilder().url(modifiedUrl).build()
            return chain.proceed(modifiedRequest)
        } catch (exception: Exception) {
            throw exception
        }
    }

}