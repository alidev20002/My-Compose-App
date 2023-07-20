package com.example.composeproject

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeproject.data.local.db.MovieDatabase
import com.example.composeproject.data.local.db.entities.Movie
import com.example.composeproject.ui.CryptoPage
import com.example.composeproject.ui.FullMoviePage
import com.example.composeproject.ui.MoviePage
import com.example.composeproject.ui.SplashScreen
import com.example.composeproject.ui.TaskList
import com.example.composeproject.ui.theme.ComposeProjectTheme
import com.example.composeproject.viewmodel.CryptoViewModel
import com.example.composeproject.viewmodel.MovieViewModel
import com.example.composeproject.viewmodel.TaskViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class MainActivity : ComponentActivity() {

    private val TAG = "alitest"
    private val movieViewModel by viewModels<MovieViewModel>()
    private val cryptoViewModel by viewModels<CryptoViewModel>()
    private val taskViewModel by viewModels<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // this is test area and will be removed soon

        val db = MovieDatabase.getDatabase(this)
        val movieDao = db.movieDao()
        val movie = Movie(
            title = "Avengers",
            poster = "",
            year = "2021",
            country = "US",
            imdbRating = "8.2",
            genres = listOf("Action", "Sci-Fi", "Fantasy", "Adventure"),
            images = emptyList()
        )
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.insertMovie(movie)
        }

        val movies = movieDao.getAllMovies()

        lifecycleScope.launch {
            movies.collect {
                Log.i("alitest", "onCreate: $it")
            }
        }

        // #########################################

        setContent {
            var isLightTheme by rememberSaveable { mutableStateOf(true) }
            val navController = rememberNavController()

            ComposeProjectTheme (darkTheme = !isLightTheme) {

                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    NavHost(navController = navController,
                        startDestination = "splash") {

                        composable("splash") {
                            SplashScreen(navController)
                        }
                        composable("movie") {
                            MoviePage(movieViewModel,
                                isLightTheme,
                                navController,
                                onChangeTheme = {isLightTheme = !isLightTheme}
                            )

                            movieViewModel.getMovieList()
                        }
                        composable("fullMovie") {
                            FullMoviePage(movieViewModel.fullMovieResponse,
                                isLightTheme,
                                navController) {
                                isLightTheme = !isLightTheme
                            }

                            movieViewModel.getFullMovie()
                        }
                        composable("crypto") {
                            CryptoPage(cryptoViewModel.cryptoStats)
                            cryptoViewModel.getCryptoStats()
                        }
                        composable("task") {
                            TaskList(taskViewModel)
                        }
                    }
                }
            }
        }
    }
}

/*
@Preview(widthDp = 360, heightDp = 640)
@Composable
fun DefaultPreview() {
    var isLightTheme by remember {
        mutableStateOf(true)
    }
    val navController = rememberNavController()
    ComposeProjectTheme (darkTheme = !isLightTheme) {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            NavHost(navController = navController, startDestination = "home") {
                composable("splash") {
                    SplashScreen(navController)
                }
                composable("movie") {
//                    MoviePage(MovieData(emptyList()), isLightTheme) {
//                        isLightTheme = !isLightTheme
//                    }
                }
                composable("crypto") {
                    CryptoPage()
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreviewDark() {
    var isLightTheme by remember {
        mutableStateOf(false)
    }
    val navController = rememberNavController()
    ComposeProjectTheme (darkTheme = !isLightTheme) {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            NavHost(navController = navController, startDestination = "home") {
                composable("splash") {
                    SplashScreen(navController)
                }
                composable("movie") {
//                    MoviePage(MovieData(emptyList()), isLightTheme) {
//                        isLightTheme = !isLightTheme
//                    }
                }
                composable("crypto") {
                    CryptoPage()
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PartPreview() {
}**/