package com.example.composetemplate.data.repository.exchange

import androidx.paging.PagingData
import com.example.composetemplate.data.datasource.remote.exchange.RemoteExchangeDataSource
import com.example.composetemplate.data.models.local.Exchange
import com.example.composetemplate.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultExchangeRepository @Inject constructor(
    private val remoteExchangeDataSource: RemoteExchangeDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ExchangeRepository {
    override fun getExchange(
        perPage: Int, page: Int
    ): Flow<PagingData<Exchange>> =
        remoteExchangeDataSource.getExchange(perPage = perPage, page = page).flowOn(ioDispatcher)
}