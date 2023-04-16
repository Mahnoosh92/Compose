package com.example.composetemplate.data.models.remote

import com.example.composetemplate.data.models.local.CoinMarket
import com.google.gson.annotations.SerializedName

data class RemoteCoinMarket(
    @SerializedName("id") val id: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("symbol") val symbol: String?
) {
    fun toCoinMarket() = CoinMarket(id = id, image = image, name = name, symbol = symbol)
}