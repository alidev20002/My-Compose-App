package com.example.composeproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composeproject.data.local.db.MovieDatabase
import com.example.composeproject.data.local.db.MovieLocalDataSource
import com.example.composeproject.data.network.MovieRemoteDataSource
import com.example.composeproject.data.network.api.ApiMovie
import com.example.composeproject.data.repository.MovieRepository
import com.example.composeproject.ui.viewmodels.MovieViewModelFactory
import com.example.composeproject.viewmodel.CryptoViewModel
import com.example.composeproject.viewmodel.MovieViewModel
import com.example.composeproject.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tag = "alitest"
    private val movieViewModel by viewModels<MovieViewModel>()
    private val cryptoViewModel by viewModels<CryptoViewModel>()
    private val taskViewModel by viewModels<TaskViewModel>()

    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // this is test area and will be removed soon

        val db = MovieDatabase.getDatabase(this)
        val movieDao = db.movieDao()
        val movieLocalDataSource = MovieLocalDataSource(movieDao)
        val movieRemoteDataSource = MovieRemoteDataSource(ApiMovie.getInstance())
        val movieRepository = MovieRepository(movieLocalDataSource, movieRemoteDataSource)
        val movieViewModel = ViewModelProvider(this, MovieViewModelFactory(movieRepository)).get(com.example.composeproject.ui.viewmodels.MovieViewModel::class.java)


        val movies = movieViewModel.allMovies
        movieViewModel.syncMovies()
        Log.i(tag, "onCreate: Start")

        lifecycleScope.launch {
            movies.collect {
                Log.i(tag, "onCreate: $it")
            }
        }

        setContent {
            val movieItems by movies.collectAsState(initial = emptyList())
            LazyColumn(modifier = Modifier) {
                itemsIndexed(movieItems, key = {index, _ -> index}) {_, item ->
                    Row(modifier = Modifier.padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        GlideImage(
                            model = item.poster,
                            contentDescription = "poster",
                            modifier = Modifier.clip(CircleShape).size(50.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = item.title)
                        Text(text = item.year)
                    }
                }
            }
        }

        // #########################################

//        setContent {
//            var isLightTheme by rememberSaveable { mutableStateOf(true) }
//            val navController = rememberNavController()
//
//            ComposeProjectTheme (darkTheme = !isLightTheme) {
//
//                Surface(modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background) {
//
//                    NavHost(navController = navController,
//                        startDestination = "splash") {
//
//                        composable("splash") {
//                            SplashScreen(navController)
//                        }
//                        composable("movie") {
//                            MoviePage(movieViewModel,
//                                isLightTheme,
//                                navController,
//                                onChangeTheme = {isLightTheme = !isLightTheme}
//                            )
//
//                            movieViewModel.getMovieList()
//                        }
//                        composable("fullMovie") {
//                            FullMoviePage(movieViewModel.fullMovieResponse,
//                                isLightTheme,
//                                navController) {
//                                isLightTheme = !isLightTheme
//                            }
//
//                            movieViewModel.getFullMovie()
//                        }
//                        composable("crypto") {
//                            CryptoPage(cryptoViewModel.cryptoStats)
//                            cryptoViewModel.getCryptoStats()
//                        }
//                        composable("task") {
//                            TaskList(taskViewModel)
//                        }
//                    }
//                }
//            }
//        }
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