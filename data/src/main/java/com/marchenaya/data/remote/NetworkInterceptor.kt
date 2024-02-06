package com.marchenaya.data.remote

import com.marchenaya.data.exception.HttpClientException
import com.marchenaya.data.exception.NotFoundException
import com.marchenaya.data.exception.RequestFailException
import com.marchenaya.data.exception.ServerException
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
                .addQueryParameter("apiKey", ApiConstants.ApiKey)
                .build()
            val modifiedRequest = originalRequest.newBuilder().url(modifiedUrl).build()
            val response = chain.proceed(modifiedRequest)
            checkResponse(response)
            return response
        } catch (exception: Exception) {
            throw exception
        }
    }

    private fun checkResponse(response: Response) {
        if (!response.isSuccessful) {
            when (response.code) {
                404 -> throw NotFoundException()
                in 400..499 -> throw HttpClientException()
                in 500..599 -> throw ServerException()
                else -> throw RequestFailException()
            }
        }
    }

}