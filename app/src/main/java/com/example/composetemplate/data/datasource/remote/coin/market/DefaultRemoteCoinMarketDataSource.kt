package com.example.composetemplate.data.datasource.remote.coin.market

import com.example.composetemplate.data.api.ApiService
import com.example.composetemplate.data.models.remote.RemoteCoinMarket
import com.example.composetemplate.data.models.remote.RemoteCoinMarketDetail
import com.example.composetemplate.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class DefaultRemoteCoinMarketDataSource @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    RemoteCoinMarketDataSource {

    override fun getCoins(
        currency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean,
        locale: String
    ) = flow {
        emit(
            apiService.getCoinMarket(
                currency = currency,
                order = order,
                perPage = perPage,
                page = page,
                sparkline = sparkline,
                locale = locale
            )
        )
    }
        .flowOn(ioDispatcher)

    override fun getCoinMarketDetails(id: String): Flow<Response<RemoteCoinMarketDetail>> = flow {
        emit(apiService.getCoinMarketDetail(id = id))
    }
        .flowOn(ioDispatcher)
}