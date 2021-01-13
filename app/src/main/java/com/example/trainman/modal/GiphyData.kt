package com.example.trainman.modal


import androidx.room.*
import com.example.trainman.utilities.DataConverter
import com.example.trainman.utilities.ImagesConverters
import com.example.trainman.utilities.PaginationConverter
import com.google.gson.annotations.SerializedName

@Entity
data class GiphyData(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @SerializedName("data")
    @TypeConverters(DataConverter::class) val data: MutableList<Data>?,
    @SerializedName("pagination")
    @TypeConverters(PaginationConverter::class) val pagination: Pagination?
) {
    data class Data(
        @PrimaryKey(autoGenerate = true)
        var idData: Long = 0,
        @SerializedName("images")
        @TypeConverters(ImagesConverters::class) val images: Images,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String
    )

    data class Images(
        @PrimaryKey(autoGenerate = true)
        var idImages: Long = 0,
        @SerializedName("downsized")
        @Embedded(prefix = "small_") val downsized: Downsized,
        @SerializedName("downsized_large")
        @Embedded(prefix = "large_") val downsizedLarge: DownsizedLarge
    ) {
        data class Downsized(
            @PrimaryKey(autoGenerate = true)
            var idDownsized: Long = 0,
            @SerializedName("url")
            @ColumnInfo(name = "downsizedUrl") val url: String
        )

        data class DownsizedLarge(
            @PrimaryKey(autoGenerate = true)
            var idDownsizedLarge: Long = 0,
            @SerializedName("url")
            @ColumnInfo(name = "downsizedLargeUrl") val url: String
        )
    }

    data class Pagination(
        @PrimaryKey(autoGenerate = true)
        var idPagination: Long = 0,
        @SerializedName("count")
        val count: Int?,
        @SerializedName("offset")
        val offset: Int?,
        @SerializedName("total_count")
        val totalCount: Int?
    )
}