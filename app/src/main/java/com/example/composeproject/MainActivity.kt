package com.example.composeproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.composeproject.data.local.db.daos.MovieDao
import com.example.composeproject.data.workers.CryptoWorker
import com.example.composeproject.data.workers.LogWorker
import com.example.composeproject.data.workers.MovieWorker
import com.example.composeproject.ui.screen.CryptoPage
import com.example.composeproject.ui.screen.FullMoviePage
import com.example.composeproject.ui.screen.MoviePage
import com.example.composeproject.ui.screen.SplashScreen
import com.example.composeproject.ui.screen.TaskList
import com.example.composeproject.ui.theme.ComposeProjectTheme
import com.example.composeproject.ui.viewmodel.CryptoViewModel
import com.example.composeproject.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val taskViewModel by viewModels<TaskViewModel>()

    @Inject
    lateinit var movieDao: MovieDao

    @Inject
    lateinit var workManager: WorkManager

    @SuppressLint("EnqueueWork")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val cryptoWorkRequest = OneTimeWorkRequestBuilder<CryptoWorker>()
            .addTag("crypto")
            .setConstraints(constraints)
            .build()

        val logWorkRequest = OneTimeWorkRequestBuilder<LogWorker>()
            .build()

        workManager.beginUniqueWork("crypto", ExistingWorkPolicy.KEEP, cryptoWorkRequest)
            .then(logWorkRequest)
            .enqueue()

        val movieWorkRequest = PeriodicWorkRequestBuilder<MovieWorker>(15, TimeUnit.MINUTES)
            .addTag("movieApi")
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "movieApi",
            ExistingPeriodicWorkPolicy.KEEP,
            movieWorkRequest
        )

        // #########################################

        setContent {
            val movieViewModel = viewModel<com.example.composeproject.ui.viewmodel.MovieViewModel>()
            val cryptoViewModel = viewModel<CryptoViewModel>()
            var isLightTheme by rememberSaveable { mutableStateOf(true) }
            val navController = rememberNavController()

            ComposeProjectTheme(darkTheme = !isLightTheme) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val movies = movieViewModel.allMovies.collectAsLazyPagingItems()

                    NavHost(
                        navController = navController,
                        startDestination = "movie"
                    ) {

                        composable("splash") {
                            SplashScreen(navController)
                        }
                        composable("movie") {
                            MoviePage(
                                movieViewModel,
                                movies,
                                isLightTheme,
                                navController,
                                onChangeTheme = { isLightTheme = !isLightTheme }
                            )
                        }
                        composable("fullMovie") {
                            FullMoviePage(
                                movieViewModel.movieDetail!!,
                                isLightTheme,
                                navController
                            ) {
                                isLightTheme = !isLightTheme
                            }
                        }
                        composable("crypto") {
                            CryptoPage(cryptoViewModel.cryptoStats)
                        }
                        cryptoViewModel.getCryptoStats()
                        composable("task") {
                            TaskList(taskViewModel)
                        }
                    }
                }
            }
        }
    }
}