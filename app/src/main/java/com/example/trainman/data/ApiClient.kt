package com.example.trainman.data

import com.example.trainman.modal.GiphyData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET(ApiUrls.TRENDING)
    suspend fun getTrending(@Query("api_key") apiKey: String = ApiUrls.API_KEY,
    @Query("limit") limit: Int = 27, @Query("offset") offset: Int = 0):GiphyData
}