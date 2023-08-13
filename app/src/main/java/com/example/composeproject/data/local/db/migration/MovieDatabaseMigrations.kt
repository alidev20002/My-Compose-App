package com.example.composeproject.data.local.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Remove auto-generation of the id column
        database.execSQL("CREATE TABLE movie_new (id INTEGER NOT NULL, title TEXT NOT NULL, poster TEXT NOT NULL, year TEXT NOT NULL, country TEXT NOT NULL, imdbRating TEXT NOT NULL, genres TEXT NOT NULL, images TEXT NOT NULL, PRIMARY KEY(id))")
        database.execSQL("INSERT INTO movie_new SELECT * FROM movie")
        database.execSQL("DROP TABLE movie")
        database.execSQL("ALTER TABLE movie_new RENAME TO movie")
    }
}