package com.example.trainman.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trainman.modal.GiphyData
import com.example.trainman.utilities.DataConverter
import com.example.trainman.utilities.ImagesConverters
import com.example.trainman.utilities.PaginationConverter

@Database(entities = [GiphyData::class], version = 2, exportSchema = false)
@TypeConverters(DataConverter::class, ImagesConverters::class, PaginationConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun giphyDao(): GiphyDao?

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "giphy_db"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}