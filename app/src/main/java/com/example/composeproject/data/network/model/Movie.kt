package com.example.composeproject.data.network.model

import com.example.composeproject.data.local.db.entities.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieData(@SerializedName("data") val data: List<Movie>)

data class Movie(@SerializedName("title") val title: String,
                 @SerializedName("poster") val poster: String,
                 @SerializedName("imdb_rating") val imdb_rating: String)

data class FullMovieData(@SerializedName("data") val data: List<FullMovie>)

data class FullMovie(@SerializedName("title") val title: String,
                     @SerializedName("poster") val poster: String,
                     @SerializedName("year") val year: String,
                     @SerializedName("country") val country: String,
                     @SerializedName("imdb_rating") val imdb_rating: String,
                     @SerializedName("genres") val genres: List<String>,
                     @SerializedName("images") val images: List<String>)

fun FullMovie.toEntityModel(): MovieEntity {
    return MovieEntity(
        title = this.title,
        poster = this.poster,
        year = this.year,
        country = this.country,
        imdbRating = this.imdb_rating,
        genres = this.genres,
        images = this.images
    )
}