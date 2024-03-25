package com.marchenaya.data.mapper.base

abstract class RemoteMapper<K : Any?, T : Any?> {

    suspend fun transformModelList(input: List<T>): List<K> {
        return input.mapNotNull {
            try {
                transformModelToRemote(it)
            } catch (exception: Exception) {
                onMappingError(exception)
                null
            }
        }
    }

    suspend fun transformRemoteList(input: List<K>): List<T> {
        return input.mapNotNull {
            try {
                transformRemoteToModel(it)
            } catch (exception: Exception) {
                onMappingError(exception)
                null
            }
        }
    }

    abstract suspend fun transformModelToRemote(input: T): K
    abstract suspend fun transformRemoteToModel(input: K): T
    abstract fun onMappingError(exception: Exception)
}