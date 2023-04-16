package com.example.composetemplate.data.models.local

data class CoinMarketDetail(
    val community_score: Double?,
    val country_origin: String?,
    val description: Description?,
    val symbol: String?,
)

data class Description(
    val en: String?,
)