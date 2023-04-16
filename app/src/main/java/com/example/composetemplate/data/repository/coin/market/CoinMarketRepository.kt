package com.example.composetemplate.data.repository.coin.market

import com.example.composetemplate.data.models.local.CoinMarket
import com.example.composetemplate.data.models.local.CoinMarketDetail
import com.example.composetemplate.data.models.local.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface CoinMarketRepository {
    fun getCoins(
        currency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean,
        locale: String
    ): Flow<ResultWrapper<Exception, List<CoinMarket>?>>

    fun getCoinMarketDetails(id: String): Flow<ResultWrapper<Exception, CoinMarketDetail?>>
}