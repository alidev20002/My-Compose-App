package com.example.composeproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.composeproject.R
import com.example.composeproject.model.CryptoRandom
import com.example.composeproject.model.CryptoStats

@Composable
fun CryptoPage(cryptoStats: CryptoStats) {

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        LazyColumn(modifier = Modifier
            .background(Color(0xCC23143C))
            .fillMaxSize()
            .padding(10.dp)) {

            item {

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(getRandomCrypto()) { item ->

                        Column(modifier = Modifier.width(100.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {

                            Image(painter = painterResource(id = item.logo),
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth())

                            Text(text = item.name,
                                color = Color.White)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            val cryptos = cryptoStats.data.entries.toMutableList()

            items(cryptos) { item ->

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = Color(0xCC0B1435)
                ) {

                    Column(modifier = Modifier.padding(10.dp)) {

                        Text(text = item.key.uppercase(),
                            color = Color.Yellow,
                            style = MaterialTheme.typography.h5)

                        val crypto = item.value

                        Text(text = "latest: " + crypto.latest, color = Color.White)

                        Text(text = "bestSell: " + crypto.bestSell, color = Color.White)

                        Text(text = "bestBuy: " + crypto.bestBuy, color = Color.White)

                        Text(text = "dayHigh: " + crypto.dayHigh, color = Color.White)

                        Text(text = "dayLow: " + crypto.dayLow, color = Color.White)
                    }
                }
            }
        }
    }
}

private fun getRandomCrypto(): List<CryptoRandom> {
    return listOf(
        CryptoRandom(R.drawable.btc, "BTC"),
        CryptoRandom(R.drawable.eth, "ETH"),
        CryptoRandom(R.drawable.usdt, "USDT"),
        CryptoRandom(R.drawable.trx, "TRX"),
        CryptoRandom(R.drawable.xrp, "XRP"),
        CryptoRandom(R.drawable.bnb, "BNB"),
        CryptoRandom(R.drawable.shib, "SHIB"),
        CryptoRandom(R.drawable.ada, "ADA"),
    )
}