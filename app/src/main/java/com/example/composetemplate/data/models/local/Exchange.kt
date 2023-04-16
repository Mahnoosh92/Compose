package com.example.composetemplate.data.models.local

import com.google.gson.annotations.SerializedName

data class Exchange(
    val id: String?,
    val name: String?,
    val yearEstablished: Int?,
    val country: String?,
    val description: String?,
    val url: String?,
    val image: String?,
    val hasTradingIncentive: Boolean?,
    val trustScore: Int?,
    val trustScoreRank: Int?,
)
