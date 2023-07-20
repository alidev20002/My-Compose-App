package com.example.composeproject.data.local.db.converters

import androidx.room.TypeConverter

class MovieConverter {

    @TypeConverter
    private fun stringToList(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    private fun listToString(values: List<String>): String {
        return values.joinToString(separator = ",")
    }

}