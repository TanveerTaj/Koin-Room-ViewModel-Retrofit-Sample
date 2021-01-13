package com.example.trainman

import com.example.trainman.data.ApiClient
import com.example.trainman.data.RequestConst
import com.example.trainman.viewmodel.GiphyViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val viewModel = module {
    viewModel { GiphyViewModel(get()) }
}

val apiModule = module {
    fun provideTrainmanApi(retrofit: Retrofit): ApiClient =
        retrofit.create(ApiClient::class.java)
    single { provideTrainmanApi(get()) }
}

val retrofitModule = module {
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(RequestConst.gson))
            .client(httpClient)
            .build()
    }

    fun provideHttpClient(): OkHttpClient = RequestConst.okHttpClient

    single { provideRetrofit(get()) }
    single { provideHttpClient() }
}