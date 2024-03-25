package com.marchenaya.data.di

import com.marchenaya.data.BuildConfig
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceComponentImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TraceModule {

    @Singleton
    @Provides
    fun traceComponent(): TraceComponent = TraceComponentImpl(BuildConfig.DEBUG)

}