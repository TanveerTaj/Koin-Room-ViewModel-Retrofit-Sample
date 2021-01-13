package com.example.trainman.utilities

import androidx.room.TypeConverter
import com.example.trainman.modal.GiphyData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PaginationConverter {
    @TypeConverter
    fun toPagination(data: GiphyData.Pagination?): String? {
        if (data == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<GiphyData.Pagination>() {}.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun fromPagination(dataString: String?): GiphyData.Pagination? {
        if (dataString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<GiphyData.Pagination?>() {}.type
        return gson.fromJson(dataString, type)
    }
}