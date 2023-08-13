package com.example.composeproject.data.workers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.composeproject.data.network.api.ApiCrypto
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@HiltWorker
class CryptoWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(ctx, params) {

    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        makeStatusNotification("Get data from api...", applicationContext)
        return try {
            val apiService = ApiCrypto()
            val crypto = apiService.getPrices()
            val jsonData = Json.encodeToString(crypto)
            val outPutData = Data.Builder()
                .put("crypto", jsonData)
                .build()
            Log.i("alitest", "doWork: $outPutData")
            makeStatusNotification("Data is ready!\n$outPutData", applicationContext)
            Result.success(outPutData)
        } catch (e: Exception) {
            Log.i("alitest", "doWork: ${e.printStackTrace()}")
            Result.failure()
        }
    }
}