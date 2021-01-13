package com.example.trainman.utilities

import androidx.room.TypeConverter
import com.example.trainman.modal.GiphyData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

public class ImagesConverters {

    /**
     * Convert a a list of Images to a Json
     */
    @TypeConverter
    fun fromImagesJson(stat: GiphyData.Images): String {
        return Gson().toJson(stat)
    }

    /**
     * Convert a json to a list of Images
     */
    @TypeConverter
    fun toImagesList(jsonImages: String): GiphyData.Images {
        val notesType = object : TypeToken<GiphyData.Images>() {}.type
        return Gson().fromJson(jsonImages, notesType)
    }
}