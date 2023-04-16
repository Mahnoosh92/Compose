package com.example.composetemplate.data.datasource.remote.exchange

import androidx.paging.PagingData
import com.example.composetemplate.data.models.local.Exchange
import kotlinx.coroutines.flow.Flow

interface RemoteExchangeDataSource {
    fun getExchange(
        perPage: Int, page: Int
    ): Flow<PagingData<Exchange>>
}