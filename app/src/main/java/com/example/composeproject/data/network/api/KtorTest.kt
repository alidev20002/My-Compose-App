package com.example.composeproject.data.network.api

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class KtorTest {

    private var currentToken = RefreshAccessToken()

    private val client = HttpClient(OkHttp).config {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "bookingmastermind.pythonanywhere.com"
            }
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("Ktor", message)
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(Auth) {
            bearer {
                refreshTokens {
                    val token = client.post {
                        markAsRefreshTokenRequest()
                        url("token/refresh/")
                        setBody(RefreshAccessToken(refresh = currentToken.refresh))
                    }.body<RefreshAccessToken>()
                    BearerTokens(
                        accessToken = token.access,
                        refreshToken = token.refresh
                    )
                }
            }
        }
    }

    suspend fun login(phoneNumber: String, password: String): RefreshAccessToken {
        currentToken = client.post {
            url("token/")
            setBody(User(phoneNumber, password))
        }.body()
        return currentToken
    }

    suspend fun getData(): List<User> {
        return client.get {
            url("users/")
        }.body()
    }
}

@Serializable
data class User(
    val phone: String,
    val password: String
)

@Serializable
data class RefreshAccessToken(
    val access: String = "",
    val refresh: String = ""
)