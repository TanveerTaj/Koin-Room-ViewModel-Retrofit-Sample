package com.example.trainman.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainman.data.ApiClient
import com.example.trainman.modal.GiphyData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class GiphyViewModel(private val apiClient: ApiClient) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    private var _errorMessage = MutableLiveData<String>()
    private val _errorResponse = MutableLiveData<Response<*>>()
    private val _giphyData = MutableLiveData<GiphyData>()

    fun isLoading(): LiveData<Boolean> = _isLoading
    fun errorResponse(): LiveData<Response<*>> = _errorResponse
    fun errorMessage(): LiveData<String> = _errorMessage
    fun giphyData(): LiveData<GiphyData> = _giphyData

//    init {
//        callApi()
//    }

    fun callApi(offset: Int) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val data = apiClient.getTrending(offset = offset)
                _giphyData.postValue(data)
                _isLoading.postValue(false)
            } catch (e: HttpException) {
                _errorResponse.postValue(e.response())
                _isLoading.postValue(false)
            } catch (e: Exception) {
                _isLoading.postValue(false)
                _errorMessage.postValue(e.localizedMessage)
            }
        }
    }
}