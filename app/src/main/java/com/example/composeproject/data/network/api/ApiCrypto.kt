package com.example.composeproject.data.network.api

import com.example.composeproject.data.network.model.CryptoStatsModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiCrypto {

    private val httpClient = HttpClient(OkHttp) {

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.nobitex.ir"
                path("/market/stats")
            }
        }
    }

    private val srcList: String =
        "btc,eth,ltc,usdt,xrp,bch,bnb,eos,xlm,etc,trx,doge,uni,dai,link,dot,aave,ada,shib"

    suspend fun getPrices(): CryptoStatsModel {
        return httpClient.get {
            url {
                parameters.append("srcCurrency", srcList)
                parameters.append("dstCurrency", "rls")
            }
            contentType(ContentType.Application.Json)
        }.body()
    }
}