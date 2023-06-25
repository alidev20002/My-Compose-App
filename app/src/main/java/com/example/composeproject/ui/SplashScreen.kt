package com.example.composeproject.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeproject.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0.0f) }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(painter = painterResource(id = R.drawable.alilogo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.Center)
                .scale(scale.value)
        )

        Text(text = "My Compose App",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding((scale.value * 20).dp)
        )
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(1.3f, animationSpec = tween(1800, easing = {
            OvershootInterpolator(4f).getInterpolation(it)
        }))
        delay(3000L)
        navController.navigate("movie") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }
}