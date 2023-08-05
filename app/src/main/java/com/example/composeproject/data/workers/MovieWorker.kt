package com.example.composeproject.data.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.composeproject.data.local.db.MovieLocalDataSource
import com.example.composeproject.data.network.MovieRemoteDataSource
import com.example.composeproject.data.network.model.toEntityModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class MovieWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        makeStatusNotification("start periodic movie worker...", applicationContext)
        return try {
            val movies = movieRemoteDataSource.getAllMovies(page = 1)
            movieLocalDataSource.insertOrIgnoreMovies(
                movies.map {
                    it.toEntityModel()
                }
            )
            makeStatusNotification("Database Updated Successfully!", applicationContext)
            Result.success()
        } catch (e: Exception) {
            Log.i("alitest", "doWork: ${e.printStackTrace()}")
            Result.failure()
        }
    }
}