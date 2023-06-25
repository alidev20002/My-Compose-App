package com.example.composeproject.model

import com.google.gson.annotations.SerializedName

data class CryptoRandom(val logo: Int, val name: String)

data class Crypto(@SerializedName("bestSell") val bestSell: String,
                  @SerializedName("bestBuy") val bestBuy: String,
                  @SerializedName("latest") val latest: String,
                  @SerializedName("dayLow") val dayLow: String,
                  @SerializedName("dayHigh") val dayHigh: String)

data class CryptoStats(@SerializedName("stats") val data: Map<String, Crypto>)