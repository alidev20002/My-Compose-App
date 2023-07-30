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
    val stats: Map<String, CryptoModel>
)