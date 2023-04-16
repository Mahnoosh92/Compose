package com.example.composetemplate.data.models.remote

import com.example.composetemplate.data.models.local.Exchange
import com.google.gson.annotations.SerializedName

data class RemoteExchange(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("year_established") val yearEstablished: Int?,
    @SerializedName("country") val country: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("has_trading_incentive") val hasTradingIncentive: Boolean?,
    @SerializedName("trust_score") val trustScore: Int?,
    @SerializedName("trust_score_rank") val trustScoreRank: Int?,
) {
    fun toExchange() = Exchange(
        id = id,
        name = name,
        yearEstablished = yearEstablished,
        country = country,
        description = description,
        url = url,
        image = image,
        hasTradingIncentive = hasTradingIncentive,
        trustScore = trustScore,
        trustScoreRank = trustScoreRank
    )
}
