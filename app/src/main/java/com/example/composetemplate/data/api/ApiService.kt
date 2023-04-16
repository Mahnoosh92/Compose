package com.example.composetemplate.data.api

import com.example.composetemplate.data.models.remote.RemoteCoinMarket
import com.example.composetemplate.data.models.remote.RemoteCoinMarketDetail
import com.example.composetemplate.data.models.remote.RemoteExchange
import com.example.composetemplate.data.models.remote.RemoteTrendList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/trending")
    suspend fun getTrending(): Response<RemoteTrendList>

    @GET("exchanges")
    suspend fun getExchange(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
    ): Response<List<RemoteExchange>>

    @GET("coins/markets")
    suspend fun getCoinMarket(
        @Query("vs_currency") currency: String,
        @Query("order") order: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("sparkline") sparkline: Boolean,
        @Query("locale") locale: String,
    ): Response<List<RemoteCoinMarket>>

    @GET("coins/{id}")
    suspend fun getCoinMarketDetail(
        @Path("id") id: String
    ): Response<RemoteCoinMarketDetail>
}