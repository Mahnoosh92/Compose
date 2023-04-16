package com.example.composetemplate.data.repository.trend

import com.example.composetemplate.data.datasource.remote.trend.RemoteTrendDataSource
import com.example.composetemplate.data.models.local.ResultWrapper
import com.example.composetemplate.data.models.local.Trend
import com.example.composetemplate.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultTrendRepository @Inject constructor(
    private val remoteTrendDataSource: RemoteTrendDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : TrendRepository {
    override suspend fun getTrending(): Flow<ResultWrapper<Exception, List<Trend?>?>> =
        withContext(ioDispatcher) {
            remoteTrendDataSource.getTrending()
        }
}