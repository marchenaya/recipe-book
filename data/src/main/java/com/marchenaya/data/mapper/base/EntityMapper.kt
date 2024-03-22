package com.marchenaya.data.mapper.base

abstract class EntityMapper<K : Any, T : Any> {
    suspend fun transformModelList(input: List<T>): List<K> {
        return input.mapNotNull {
            try {
                transformModelToEntity(it)
            } catch (e: Exception) {
                onMappingError(e)
                null
            }
        }
    }

    suspend fun transformEntityList(input: List<K>): List<T> {
        return input.mapNotNull {
            try {
                transformEntityToModel(it)
            } catch (e: Exception) {
                onMappingError(e)
                null
            }
        }
    }

    abstract suspend fun transformEntityToModel(input: K): T
    abstract suspend fun transformModelToEntity(input: T): K
    abstract fun onMappingError(error: Exception)
}