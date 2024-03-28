package com.marchenaya.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marchenaya.data.BuildConfig
import com.marchenaya.data.exception.WrongBaseUrlException
import com.marchenaya.data.remote.api.ApiConstants.BASE_URL
import com.marchenaya.data.remote.api.RecipesApi
import com.marchenaya.data.remote.network.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RecipeRetrofit

    @Singleton
    @Provides
    fun provideOkHttpClient(networkInterceptor: NetworkInterceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(networkInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
        }.build()


    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @RecipeRetrofit
    fun provideRecipeRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        val baseUrl = BASE_URL.toHttpUrlOrNull() ?: throw WrongBaseUrlException()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideRecipesApi(@RecipeRetrofit retrofit: Retrofit): RecipesApi = retrofit.create()

}