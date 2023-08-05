package com.example.composeproject.data.workers

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.composeproject.R
import com.example.composeproject.data.network.api.ApiCrypto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CryptoWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

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

    fun makeStatusNotification(message: String, context: Context) {
        val channelId = "VERBOSE_NOTIFICATION"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "VERBOSE_NOTIFICATION_CHANNEL_NAME"
            val description = "VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
            notificationManager?.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Work Request")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(1000, 1000))

        NotificationManagerCompat.from(context).notify(1, builder.build())
    }
}