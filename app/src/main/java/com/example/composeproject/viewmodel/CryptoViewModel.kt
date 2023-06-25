package com.example.composeproject.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeproject.api.ApiCrypto
import com.example.composeproject.model.CryptoStats
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CryptoViewModel: ViewModel() {

    var cryptoStats by mutableStateOf(CryptoStats(emptyMap()))
    private var errorMessage: String by mutableStateOf("")
    private val srcList: String = "btc,eth,ltc,usdt,xrp,bch,bnb,eos,xlm,etc,trx,doge,uni,dai,link,dot,aave,ada,shib"
    private var isStart: Boolean by mutableStateOf(false)

    fun getCryptoStats() {
        if (!isStart) {
            isStart = true
            viewModelScope.launch {
                while (true) {
                    val apiService = ApiCrypto.getInstance()
                    try {
                        val crypto = apiService.getData(srcList)
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