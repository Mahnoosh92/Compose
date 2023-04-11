package com.example.composetemplate.data.models.remote

import com.example.composetemplate.data.models.local.Coin
import com.google.gson.annotations.SerializedName

data class RemoteCoin(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String
) {
    fun toCoin() = Coin(id = id, name = name, symbol = symbol)
}