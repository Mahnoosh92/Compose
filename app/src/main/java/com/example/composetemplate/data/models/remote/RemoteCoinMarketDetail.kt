package com.example.composetemplate.data.models.remote

import com.example.composetemplate.data.models.local.CoinMarketDetail
import com.example.composetemplate.data.models.local.Description
import com.google.gson.annotations.SerializedName

data class RemoteCoinMarketDetail(
    @SerializedName("community_score") val community_score: Double?,
    @SerializedName("country_origin") val country_origin: String?,
    @SerializedName("description") val description: RemoteDescription?,
    @SerializedName("symbol") val symbol: String?,
) {
    fun toCoinMarketDetail() = CoinMarketDetail(
        community_score = community_score,
        country_origin = country_origin,
        description = description?.toDescription(),
        symbol = symbol
    )
}

data class RemoteDescription(
    @SerializedName("en") val en: String?,
) {
    fun toDescription() = Description(en = en)
}