package com.example.composeproject.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class MovieWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        makeStatusNotification("Get data from api...", applicationContext)
        return try {
            Result.success()
        } catch (e: Exception) {
            Log.i("alitest", "doWork: ${e.printStackTrace()}")
            Result.failure()
        }
    }
}