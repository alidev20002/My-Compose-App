package com.example.composeproject.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CryptoModel(
    val bestSell: String,
    val bestBuy: String,
    val latest: String,
    val dayLow: String,
    val dayHigh: String
)

@Serializable
data class CryptoStatsModel(
    val status: String,
    val stats: Map<String, CryptoModel>
)