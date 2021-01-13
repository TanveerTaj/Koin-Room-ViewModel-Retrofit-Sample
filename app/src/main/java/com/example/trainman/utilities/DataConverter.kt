package com.example.trainman.utilities

import androidx.room.TypeConverter
import com.example.trainman.modal.GiphyData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun fromDataList(data: List<GiphyData.Data?>?): String? {
        if (data == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<GiphyData.Data?>?>() {}.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toDataList(dataString: String?): List<GiphyData.Data>? {
        if (dataString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<GiphyData.Data?>?>() {}.type
        return gson.fromJson(dataString, type)
    }
}