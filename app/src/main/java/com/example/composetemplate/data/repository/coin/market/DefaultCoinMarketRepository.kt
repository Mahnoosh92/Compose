package com.example.composetemplate.data.repository.coin.market

import com.example.composetemplate.data.datasource.remote.coin.market.RemoteCoinMarketDataSource
import com.example.composetemplate.data.models.local.CoinMarket
import com.example.composetemplate.data.models.local.CoinMarketDetail
import com.example.composetemplate.data.models.local.ResultWrapper
import com.example.composetemplate.di.IoDispatcher
import com.example.composetemplate.utils.getApiError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultCoinMarketRepository @Inject constructor(
    private val remoteCoinMarketDataSource: RemoteCoinMarketDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CoinMarketRepository {
    override fun getCoins(
        currency: String, order: String, perPage: Int, page: Int, sparkline: Boolean, locale: String
    ): Flow<ResultWrapper<Exception, List<CoinMarket>?>> {
        return remoteCoinMarketDataSource.getCoins(
            currency = currency,
            order = order,
            perPage = perPage,
            page = page,
            sparkline = sparkline,
            locale = locale
        ).map { response ->
            if (response.isSuccessful) {
                ResultWrapper.build { response.body()?.map { it.toCoinMarket() } }
            } else {
                ResultWrapper.build { throw Exception(response.getApiError()?.error) }
            }

        }.flowOn(ioDispatcher)

    }

    override fun getCoinMarketDetails(id: String): Flow<ResultWrapper<Exception, CoinMarketDetail?>> {
        return remoteCoinMarketDataSource.getCoinMarketDetails(id = id).map { response ->
                if (response.isSuccessful) {
                    ResultWrapper.build { response.body()?.toCoinMarketDetail() }
                } else {
                    ResultWrapper.build { throw Exception(response.getApiError()?.error) }
                }

            }.flowOn(ioDispatcher)
    }
}