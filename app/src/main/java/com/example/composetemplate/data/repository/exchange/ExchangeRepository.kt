package com.example.composetemplate.data.repository.exchange

import androidx.paging.PagingData
import com.example.composetemplate.data.models.local.Exchange
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    fun getExchange(
        perPage: Int,
        page: Int
    ): Flow<PagingData<Exchange>>
}