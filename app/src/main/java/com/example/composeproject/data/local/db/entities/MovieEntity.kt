package com.example.composeproject.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composeproject.data.network.model.FullMovie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val poster: String,
    val year: String,
    val country: String,
    val imdbRating: String,
    val genres: List<String>,
    val images: List<String>
)

fun MovieEntity.toFullMovieModel(): FullMovie {
    return FullMovie(
        id = id,
        title = this.title,
        poster = this.poster,
        year = this.year,
        country = this.country,
        imdb_rating = this.imdbRating,
        genres = this.genres,
        images = this.images
    )
}
