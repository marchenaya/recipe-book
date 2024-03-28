package com.marchenaya.data.mapper.base

abstract class RemoteMapper<K : Any?, T : Any?> {

    suspend fun transformModelList(input: List<T>): List<K> {
        return input.mapNotNull { model ->
            try {
                transformModelToRemote(model)
            } catch (exception: Exception) {
                onMappingError(exception)
                null
            }
        }
    }

    suspend fun transformRemoteList(input: List<K>): List<T> {
        return input.mapNotNull { remote ->
            try {
                transformRemoteToModel(remote)
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