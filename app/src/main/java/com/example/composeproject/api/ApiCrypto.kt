package com.example.composeproject.api

import com.example.composeproject.model.CryptoStats
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCrypto {

    @GET("stats")
    suspend fun getData(@Query("srcCurrency") src: String, @Query("dstCurrency") dst: String = "rls") : CryptoStats

    companion object {
        var apiService: ApiCrypto? = null
        fun getInstance() : ApiCrypto {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.nobitex.ir/market/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiCrypto::class.java)
            }
            return apiService!!
        }
    }
}