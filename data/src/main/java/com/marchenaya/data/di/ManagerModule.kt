package com.marchenaya.data.di

import com.marchenaya.data.remote.network.NetworkManager
import com.marchenaya.data.remote.network.NetworkManagerImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ManagerModule {

    @Provides
    @Reusable
    fun networkManager(networkManagerImpl: NetworkManagerImpl): NetworkManager = networkManagerImpl

}
