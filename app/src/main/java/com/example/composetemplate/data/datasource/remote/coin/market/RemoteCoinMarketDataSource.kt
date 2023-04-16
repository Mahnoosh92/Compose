package com.example.composetemplate.data.datasource.remote.coin.market

import com.example.composetemplate.data.models.remote.RemoteCoinMarket
import com.example.composetemplate.data.models.remote.RemoteCoinMarketDetail
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Query

interface RemoteCoinMarketDataSource {
    fun getCoins(
        currency: String,
        order: String,
        perPage: Int,
        page: Int,
        sparkline: Boolean,
        locale: String
    ): Flow<Response<List<RemoteCoinMarket>>>

    fun getCoinMarketDetails(id: String): Flow<Response<RemoteCoinMarketDetail>>
}