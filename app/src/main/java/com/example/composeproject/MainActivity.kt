package com.example.composeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composeproject.ui.FullMoviePage
import com.example.composeproject.ui.MoviePage
import com.example.composeproject.ui.theme.ComposeProjectTheme
import com.example.composeproject.viewmodel.CryptoViewModel
import com.example.composeproject.viewmodel.MovieViewModel
import com.example.composeproject.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        // #########################################

        setContent {
            val movieViewModel = viewModel<com.example.composeproject.ui.viewmodel.MovieViewModel>()
            var isLightTheme by rememberSaveable { mutableStateOf(true) }
            val navController = rememberNavController()

            ComposeProjectTheme (darkTheme = !isLightTheme) {

                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    NavHost(navController = navController,
                        startDestination = "movie") {

//                        composable("splash") {
//                            SplashScreen(navController)
//                        }
                        composable("movie") {
                            MoviePage(movieViewModel,
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
//                        composable("crypto") {
//                            CryptoPage(cryptoViewModel.cryptoStats)
//                            cryptoViewModel.getCryptoStats()
//                        }
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