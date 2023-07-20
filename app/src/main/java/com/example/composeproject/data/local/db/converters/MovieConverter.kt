package com.example.composeproject.data.local.db.converters

import androidx.room.TypeConverter

class MovieConverter {

    @TypeConverter
    fun stringToList(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun listToString(values: List<String>): String {
        return values.joinToString(separator = ",")
    }

}