package com.example.composeproject.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val poster: String,
    val year: String,
    val country: String,
    val imdb_rating: String,
    val genres: List<String>,
    val images: List<String>
)
