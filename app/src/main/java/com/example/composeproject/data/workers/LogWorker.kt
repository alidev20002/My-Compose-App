package com.example.composeproject.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class LogWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return try {
            val cryptoData = inputData.getString("crypto")
            Log.i("alitest", "log worker: i got the data :) -> $cryptoData")
            Result.success()
        }catch (e: Exception) {
            Result.failure()
        }
    }

}