package com.example.composeproject.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.composeproject.data.local.db.converters.MovieConverter
import com.example.composeproject.data.local.db.daos.MovieDao
import com.example.composeproject.data.local.db.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(MovieConverter::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    // manually dependency injection
    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movie_database"
                    ).build()
                    INSTANCE = instance
                }
                INSTANCE!!
            }
        }
    }
}