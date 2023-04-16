package com.example.composetemplate.data.datasource.remote.trend

import com.example.composetemplate.data.api.ApiService
import com.example.composetemplate.data.models.local.ResultWrapper
import com.example.composetemplate.data.models.local.Trend
import com.example.composetemplate.di.IoDispatcher
import com.example.composetemplate.utils.getApiError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultRemoteTrendDataSource @Inject constructor(
    private val apiService: ApiService, @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteTrendDataSource {
    override suspend fun getTrending(): Flow<ResultWrapper<Exception, List<Trend?>?>> {
        val response = apiService.getTrending()

        return flow {
            if (response.isSuccessful) {
                emit(ResultWrapper.build {
                    response.body()?.coins?.filterNotNull()?.map {
                        it.toTrendItem().item
                    }
                })
            } else {
                emit(ResultWrapper.build { throw Exception(response.getApiError()?.error) })
            }
        }.catch { }.flowOn(ioDispatcher)
    }
}