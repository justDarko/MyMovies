package com.solo.mymovies.domain.useCase

import com.solo.mymovies.data.CustomResult
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<in Params, out Type> where Type : Any {
    abstract suspend fun execute(params: Params):
            Flow<CustomResult<Type>>

    suspend fun execute(): Flow<CustomResult<Type>> {
        return execute(Unit as Params)
    }
}