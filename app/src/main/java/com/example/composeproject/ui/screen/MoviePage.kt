package com.example.composeproject.ui.screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composeproject.ui.viewmodel.MovieViewModel
import com.example.composeproject.R
import com.example.composeproject.data.network.model.FullMovie
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MoviePage(
    movieViewModel: MovieViewModel,
    movies: LazyPagingItems<FullMovie>,
    isLightTheme: Boolean,
    navController: NavController,
    onChangeTheme: () -> Unit,
) {

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        var textfield by remember { mutableStateOf("") }
        val isPortrait =
            LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

        val selectedItem by remember { mutableStateOf(0) }
        val items = listOf("Previous", "Next")

        val scrollState = rememberLazyGridState()

        Scaffold(modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            backgroundColor = MaterialTheme.colors.background,
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .height(85.dp)
                        .padding(top = 15.dp),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Open Navigation Drawer",
                                tint = MaterialTheme.colors.onBackground
                            )
                        }
                    },
                    title = {
                        TextField(
                            value = textfield,
                            onValueChange = {
                                textfield = it
                                movieViewModel.searchMovie(it)
                            },
                            leadingIcon = {
                                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                            },
                            modifier = Modifier
                                .padding(5.dp)
                                .height(70.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = MaterialTheme.colors.surface,
                                focusedIndicatorColor = Color.Transparent,
                                textColor = MaterialTheme.colors.onSurface,
                                disabledTextColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(8.dp),
                            placeholder = { Text(text = "Search") },
                            textStyle = TextStyle(fontSize = 12.sp),
                            singleLine = true
                        )

                    },
                    actions = {
                        IconButton(
                            onClick = {
                                onChangeTheme()
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = if (isLightTheme) R.drawable.moon else R.drawable.sun),
                                contentDescription = "Open Navigation Drawer",
                                tint = MaterialTheme.colors.onBackground
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.background,
                    elevation = 0.dp
                )
            },
            bottomBar = {
                BottomNavigation(
                    backgroundColor = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
                ) {
                    items.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            label = {
                                Text(
                                    item,
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.h5
                                )
                            },
                            selected = selectedItem == index,
                            onClick = {
                                if (item == "Next") {
//                                    movieViewModel.nextPage()
                                } else {
//                                    movieViewModel.previousPage()
                                }
                            },
                            icon = {}
                        )
                    }
                }
            },
            drawerContent = {
                DrawerContent(navController = navController)
            },
            content = { paddingValues ->

                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {

                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 30.dp),
                        columns = GridCells.Fixed(3),
                        state = scrollState
                    ) {
                        val moviesList = movies.itemSnapshotList.items.filter {
                            it.poster.endsWith(".jpg")
                        }
                        items(
                            moviesList.size,
                        ) { index ->
                            movies[index]?.let {
                                if (it.poster.endsWith(".jpg")) {
                                    Card(
                                        elevation = 8.dp,
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier
                                            .padding(8.dp)
                                    ) {
                                        GlideImage(model = it.poster,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .clickable {
                                                    movieViewModel.updateMovieDetail(it)
                                                    navController.navigate("fullMovie")
                                                }
                                                .clip(
                                                    RoundedCornerShape(8.dp)
                                                )
                                                .height(150.dp),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                            }
                        }
                        movies.apply {
                            when {
                                loadState.refresh is LoadState.Loading -> {
                                    item {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .padding(16.dp)
                                        )
                                    }
                                }

                                loadState.refresh is LoadState.Error -> {
                                    // Error
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FullMoviePage(
    fullMovie: FullMovie,
    isLightTheme: Boolean,
    navController: NavController,
    onChangeTheme: () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Box(modifier = Modifier) {

                GlideImage(
                    model = fullMovie.poster,
                    contentDescription = null,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(0.dp, 0.dp, 32.dp, 32.dp)),
                    contentScale = ContentScale.Crop,
                )

                IconButton(modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp),
                    onClick = {
                        navController.navigate("movie") {
                            popUpTo("fullMovie") {
                                inclusive = true
                            }
                        }
                    }) {

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White.copy(0.4F), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                IconButton(modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp), onClick = { onChangeTheme() }) {

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White.copy(0.4F), CircleShape)
                    ) {

                        Icon(
                            painter = if (isLightTheme) painterResource(id = R.drawable.moon) else painterResource(
                                R.drawable.sun
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center),
                            tint = Color.Black
                        )
                    }
                }
            }

            Text(
                text = fullMovie.title,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(10.dp)
            )

            if (fullMovie.genres?.isNotEmpty() == true) {

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {

                    items(fullMovie.genres) { item ->

                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            border = BorderStroke(1.dp, Color.Black),
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(text = item)
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.padding(start = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.imdb),
                    contentDescription = "imdb",
                    modifier = Modifier.width(50.dp)
                )

                Text(text = fullMovie.imdb_rating, style = MaterialTheme.typography.subtitle1)

                Text(
                    text = fullMovie.country,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(start = 20.dp, end = 5.dp)
                )

                Text(
                    text = fullMovie.year,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface
                )
            }

            if (fullMovie.images?.isNotEmpty() == true) {

                LazyRow(modifier = Modifier.padding(top = 10.dp)) {

                    items(fullMovie.images) { image ->

                        Card(
                            elevation = 8.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {

                            GlideImage(
                                model = image,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(200.dp)
                                    .width(300.dp),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
    }
}