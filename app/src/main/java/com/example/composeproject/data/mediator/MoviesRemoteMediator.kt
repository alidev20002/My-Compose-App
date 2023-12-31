package com.example.composeproject.data.mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.composeproject.data.local.db.MovieLocalDataSource
import com.example.composeproject.data.local.db.entities.MovieEntity
import com.example.composeproject.data.local.keyvalue.PageDataStore
import com.example.composeproject.data.network.MovieRemoteDataSource
import com.example.composeproject.data.network.model.toEntityModel
import kotlinx.coroutines.flow.firstOrNull

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val pageDataStore: PageDataStore,
    private var page: Int = 1
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun initialize(): InitializeAction {
        page = pageDataStore.readPageNumber().firstOrNull()
            ?: return InitializeAction.LAUNCH_INITIAL_REFRESH

        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    @ExperimentalPagingApi
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {

        return try {
            when (loadType) {
                LoadType.REFRESH -> {
                    page = 1
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }

                LoadType.APPEND -> {
                    page = pageDataStore.readPageNumber().firstOrNull()
                        ?: return MediatorResult.Success(true)
                    Log.i("alitest", "append block: $page")
                }
            }
            val movies = movieRemoteDataSource.getAllMovies(
                page = page
            )

            Log.i("alitest", "after block: $movies")

            pageDataStore.writePageNumber(++page)

            movieLocalDataSource.insertOrIgnoreMovies(
                movies.map {
                    it.toEntityModel()
                }
            )
            MediatorResult.Success(
                endOfPaginationReached = movies.isEmpty()
            )
        } catch (e: Exception) {
            Log.i("alitest", "load: $e")
            return MediatorResult.Error(e)
        }
    }
}