package com.example.composetemplate.data.repository.trend

import com.example.composetemplate.data.models.local.ResultWrapper
import com.example.composetemplate.data.models.local.Trend
import kotlinx.coroutines.flow.Flow

interface TrendRepository {
    suspend fun getTrending(): Flow<ResultWrapper<Exception, List<Trend?>?>>
}