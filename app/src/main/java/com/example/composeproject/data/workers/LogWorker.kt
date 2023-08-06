package com.example.composeproject.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class LogWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return try {
            Result.success()
        }catch (e: Exception) {
            Result.failure()
        }
    }

}