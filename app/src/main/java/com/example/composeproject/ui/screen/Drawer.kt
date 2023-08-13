package com.example.composeproject.ui.screen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composeproject.R
import com.example.composeproject.data.network.model.DrawerItem

@Composable
fun DrawerContent(navController: NavController) {

    val itemsList = getDrawerItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 36.dp)
    ) {

        item {

            Image(
                modifier = Modifier
                    .size(size = 120.dp)
                    .clip(shape = CircleShape),
                painter = painterResource(id = R.drawable.alilogo),
                contentDescription = "Profile Image"
            )

            Text(
                modifier = Modifier
                    .padding(top = 12.dp),
                text = "AliDev",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
                text = "ali.khadangi@mail.com",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onBackground
            )
        }

        items(itemsList) { item ->
            DrawerListItem(item = item, navController)
        }
    }
}

@Composable
private fun DrawerListItem(item: DrawerItem, navController: NavController) {

    val activity = (LocalContext.current as? Activity)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .clickable {
                if (item.title.contains("Movie")) {
                    navController.navigate("movie")
                } else if (item.title.contains("Crypto")) {
                    navController.navigate("crypto")
                } else if (item.title.contains("Task")) {
                    navController.navigate("task")
                } else if (item.title.contains("Exit")) {
                    activity?.finish()
                }
            },
        verticalAlignment = Alignment.CenterVertically) {

        Icon (imageVector = item.icon,
            modifier = Modifier.size(30.dp),
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground)

        Text(text = item.title, modifier = Modifier.padding(10.dp))
    }
}

fun getDrawerItems(): List<DrawerItem> {
    return listOf(
        DrawerItem(Icons.Filled.Face, "Movie Page"),
        DrawerItem(Icons.Filled.Star, "Crypto Page"),
        DrawerItem(Icons.Filled.Edit, "Task Page"),
        DrawerItem(Icons.Filled.Close, "Exit The App"),
    )
}