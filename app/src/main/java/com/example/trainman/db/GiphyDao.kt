package com.example.trainman.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trainman.modal.GiphyData

@Dao
abstract class GiphyDao {
    @Query("SELECT * FROM GiphyData")
    abstract fun getAll(): GiphyData

    @Insert
    abstract fun insertAll(vararg data: GiphyData)
}