package com.example.composetemplate.data.models.local

import com.example.composetemplate.data.models.remote.RemoteTrend
import com.google.gson.annotations.SerializedName

data class TrendList(val coins: List<TrendItem>?)

data class TrendItem(@SerializedName("item") val item: Trend?)

data class Trend(
    val id: String?,
    val coinId: Int?,
    val name: String?,
    val symbol: String?,
    val marketCapRank: Int?,
    val thumb: String?,
    val small: String?,
    val large: String?,
    val slug: String?,
    val priceBtc: Float?,
    val score: Int?,
) {

}
