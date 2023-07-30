package com.example.composeproject.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeproject.data.network.api.ApiCrypto
import com.example.composeproject.data.network.model.CryptoStatsModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CryptoViewModel: ViewModel() {

    var cryptoStats by mutableStateOf(CryptoStatsModel("", emptyMap()))
    private var errorMessage: String by mutableStateOf("")
    private var isStart: Boolean by mutableStateOf(false)

    private val apiService = ApiCrypto()

    fun getCryptoStats() {
        if (!isStart) {
            isStart = true
            viewModelScope.launch {
                while (true) {
                    try {
                        val crypto = apiService.getPrices()
                        cryptoStats = crypto
                    }
                    catch (e: Exception) {
                        errorMessage = e.message.toString()
                        Log.e("message", errorMessage)
                    }
                    delay(5000)
                }
            }
        }
    }
}