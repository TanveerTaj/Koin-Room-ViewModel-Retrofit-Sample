package com.example.trainman.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object RequestConst {
    private const val GSON_DATE_FROMAT = "yyyy-MM-dd'T'HH:mm:ss"
    val gson: Gson = GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .setDateFormat(GSON_DATE_FROMAT)
        .create()

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(3, TimeUnit.MINUTES)
        .addInterceptor {
            val request = it.request().newBuilder()
                .build()
            it.proceed(request)
        }
        .build()
}