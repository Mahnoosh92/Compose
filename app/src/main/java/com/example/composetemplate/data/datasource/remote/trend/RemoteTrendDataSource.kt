package com.example.composetemplate.data.datasource.remote

import com.example.composetemplate.data.models.local.ResultWrapper
import com.example.composetemplate.data.models.local.Trend
import kotlinx.coroutines.flow.Flow

interface RemoteTrendDataSource {
    suspend fun getTrending(): Flow<ResultWrapper<Exception, List<Trend?>?>>
}