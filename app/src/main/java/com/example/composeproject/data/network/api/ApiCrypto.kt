package com.example.composeproject.data.network.api

import com.example.composeproject.data.network.model.CryptoStatsModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json

class ApiCrypto {

    private val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getPrices(): CryptoStatsModel {
        return httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.nobitex.ir"
                path("/market/stats")
            }
            contentType(ContentType.Application.Json)
        }.body()
    }
}