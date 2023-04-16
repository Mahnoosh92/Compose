package com.example.composetemplate.data.models.remote

import com.example.composetemplate.data.models.local.Trend
import com.example.composetemplate.data.models.local.TrendItem
import com.example.composetemplate.data.models.local.TrendList
import com.google.gson.annotations.SerializedName

data class RemoteTrendList(@SerializedName("coins") val coins: List<RemoteTrendItem>?)

data class RemoteTrendItem(@SerializedName("item") val item: RemoteTrend?) {
    fun toTrendItem() = TrendItem(item = item?.toTrend())
}

data class RemoteTrend(
    @SerializedName("id") val id: String?,
    @SerializedName("coin_id") val coinId: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("symbol") val symbol: String?,
    @SerializedName("market_cap_rank") val marketCapRank: Int?,
    @SerializedName("thumb") val thumb: String?,
    @SerializedName("small") val small: String?,
    @SerializedName("large") val large: String?,
    @SerializedName("slug") val slug: String?,
    @SerializedName("price_btc") val priceBtc: Float?,
    @SerializedName("score") val score: Int?,
) {
    fun toTrend() = Trend(
        id = id,
        coinId = coinId,
        name = name,
        symbol = symbol,
        marketCapRank = marketCapRank,
        thumb = thumb,
        small = small,
        large = large,
        slug = slug,
        priceBtc = priceBtc,
        score = score
    )
}
