package com.marchenaya.data.mapper.base

abstract class EntityMapper<K : Any, T : Any> {
    suspend fun transformModelList(input: List<T>): List<K> {
        return input.mapNotNull {
            try {
                transformModelToEntity(it)
            } catch (exception: Exception) {
                onMappingError(exception)
                null
            }
        }
    }

    suspend fun transformEntityList(input: List<K>): List<T> {
        return input.mapNotNull {
            try {
                transformEntityToModel(it)
            } catch (exception: Exception) {
                onMappingError(exception)
                null
            }
        }
    }

    abstract suspend fun transformEntityToModel(input: K): T
    abstract suspend fun transformModelToEntity(input: T): K
    abstract fun onMappingError(exception: Exception)
}