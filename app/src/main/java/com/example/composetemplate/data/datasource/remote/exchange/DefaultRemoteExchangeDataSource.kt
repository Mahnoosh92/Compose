package com.example.composetemplate.data.datasource.remote.exchange

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.composetemplate.data.api.ApiService
import com.example.composetemplate.data.models.local.Exchange
import com.example.composetemplate.di.IoDispatcher
import com.example.composetemplate.view.home.paging.ExchangePagingSource
import com.example.composetemplate.view.home.paging.PER_PAGE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRemoteExchangeDataSource @Inject constructor(
    private val apiService: ApiService, @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteExchangeDataSource {

    override fun getExchange(perPage: Int, page: Int): Flow<PagingData<Exchange>> {
        return Pager(config = PagingConfig(
            pageSize = perPage, enablePlaceholders = false
        ), pagingSourceFactory = {
            ExchangePagingSource(apiService = apiService)
        }).flow
    }
}