package com.example.composeproject

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
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composeproject.data.local.db.daos.MovieDao
import com.example.composeproject.data.local.db.entities.MovieEntity
import com.example.composeproject.ui.screen.CryptoPage
import com.example.composeproject.ui.screen.FullMoviePage
import com.example.composeproject.ui.screen.MoviePage
import com.example.composeproject.ui.theme.ComposeProjectTheme
import com.example.composeproject.viewmodel.CryptoViewModel
import com.example.composeproject.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val taskViewModel by viewModels<TaskViewModel>()

    @Inject
    lateinit var movieDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // this is test area and will be removed soon
        lifecycleScope.launch {
//            for (i in 1..100) {
//                movieDao.insertOrIgnoreMovie(
//                    MovieEntity(i, "m$i", "", "2001", "c$i", "", emptyList(), emptyList())
//                )
//            }
        }

        // #########################################

        setContent {
            val movieViewModel = viewModel<com.example.composeproject.ui.viewmodel.MovieViewModel>()
            val cryptoViewModel = viewModel<CryptoViewModel>()
            var isLightTheme by rememberSaveable { mutableStateOf(true) }
            val navController = rememberNavController()

            ComposeProjectTheme (darkTheme = !isLightTheme) {

                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    val movies = movieViewModel.allMovies.collectAsLazyPagingItems()

                    NavHost(navController = navController,
                        startDestination = "movie") {

//                        composable("splash") {
//                            SplashScreen(navController)
//                        }
                        composable("movie") {
                            MoviePage(
                                movieViewModel,
                                movies,
                                isLightTheme,
                                navController,
                                onChangeTheme = {isLightTheme = !isLightTheme}
                            )
                        }
                        composable("fullMovie") {
                            FullMoviePage(movieViewModel.movieDetail!!,
                                isLightTheme,
                                navController) {
                                isLightTheme = !isLightTheme
                            }
                        }
                        composable("crypto") {
                            CryptoPage(cryptoViewModel.cryptoStats)
                        }
                        cryptoViewModel.getCryptoStats()
//                        composable("task") {
//                            TaskList(taskViewModel)
//                        }
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